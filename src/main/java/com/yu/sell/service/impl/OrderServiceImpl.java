package com.yu.sell.service.impl;

import com.yu.sell.converter.OrderMaster2OrderDtoConverter;
import com.yu.sell.dataobject.OrderDetail;
import com.yu.sell.dataobject.OrderMaster;
import com.yu.sell.dataobject.ProductInfo;
import com.yu.sell.dto.CartDto;
import com.yu.sell.dto.OrderDto;
import com.yu.sell.enums.OrderStatusEnum;
import com.yu.sell.enums.PayStatusEnum;
import com.yu.sell.enums.ResultEnum;
import com.yu.sell.exception.SellException;
import com.yu.sell.repository.OrderDetailRepository;
import com.yu.sell.repository.OrderMasterRepository;
import com.yu.sell.service.OrderService;
import com.yu.sell.service.ProductService;
import com.yu.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yushizhang
 * @Date: 2019/4/29 22:33
 * @Version 1.0
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    @Transactional
    public OrderDto create(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);

       /* List<CartDto> cartDtoList = new ArrayList<>();*/

        //1查询商品（数量，价格）
        for(OrderDetail orderDetail : orderDto.getOrderDetailList()){
            ProductInfo productInfo = productService.findOne(orderDetail.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmount = productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount);

            //订单详情入库
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            orderDetailRepository.save(orderDetail);

          /*  CartDto cartDto = new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDtoList.add(cartDto);*/
        }
        //3.写入订单数据库（orderMaster和orderDetail)
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMasterRepository.save(orderMaster);
        //4.扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map( e -> new CartDto(e.getProductId(),e.getProductQuantity())).collect(Collectors.toList());
        productService.decreaseStock(cartDtoList);
        return orderDto;
    }

    @Override
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterRepository.findByOrderId(orderId);
        if(orderMaster == null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> orderDetailList = orderDetailRepository.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }

        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster,orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        List<OrderDto> orderDtoList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtoList,pageable,orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();

        //判断订单状态
        if (!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("【取消订单】订单状态不正确,orderId={},orderStatus={}",orderDto.getOrderId(),orderDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }

        //修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto,orderMaster);
        OrderMaster updateResult = orderMasterRepository.save(orderMaster);
        if (updateResult == null){
            log.error("【取消订单】更新失败，orderMaster:{}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_ERROR);
        }

        //返回库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【取消订单】订单中无商品详情,orderDto={}",orderDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);

        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(orderDetail ->
            new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity())).collect(Collectors.toList());
        productService.increaseStock(cartDtoList);

        //如果已支付，需要退款
        if(orderDto.getPayStatus().equals(PayStatusEnum.FINISH.getCode())){
            //TODO
        }

        return orderDto;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }

    public static void main(String[] args) {
        List<OrderDetail> orderDetailList =new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductPrice(new BigDecimal(5));
        orderDetail.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductPrice(new BigDecimal(5));
        orderDetail1.setProductQuantity(2);
        orderDetailList.add(orderDetail);
        BigDecimal orderAmount = new BigDecimal(String.valueOf(BigDecimal.ZERO));
        orderDetailList.stream().forEach(orderDetail3 -> {
            BigDecimal orderAmount1 = new BigDecimal(String.valueOf(BigDecimal.ZERO));
            orderAmount1 = orderDetail.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(orderAmount1);
            System.out.println(orderAmount1);
        });

    }
}
