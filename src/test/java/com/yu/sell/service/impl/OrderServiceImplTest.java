package com.yu.sell.service.impl;

import com.yu.sell.dataobject.OrderDetail;
import com.yu.sell.dto.OrderDto;
import com.yu.sell.enums.OrderStatusEnum;
import com.yu.sell.enums.PayStatusEnum;
import com.yu.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/5/3 20:02
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    private final String BUYEROPENID = "1101110";

    private final String ORDER_ID = "1556890750181681108";

    @Test
    public void create() throws Exception{
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("于是张");
        orderDto.setBuyerAddress("发展路八号院");
        orderDto.setBuyerPhone("123456789");
        orderDto.setBuyerOpenid(BUYEROPENID);

        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setProductId("123458");
        orderDetail.setProductQuantity(1);

        OrderDetail orderDetail1 = new OrderDetail();
        orderDetail1.setProductId("123456");
        orderDetail1.setProductQuantity(2);

        orderDetailList.add(orderDetail);
        orderDetailList.add(orderDetail1);
        orderDto.setOrderDetailList(orderDetailList);
        OrderDto result = orderService.create(orderDto);
        log.info("【创建订单】result={}",result);
        Assert.assertNotNull(result);
    }

    @Test
    public void findOne() throws Exception{
        OrderDto result = orderService.findOne(ORDER_ID);
        log.info("【查询单个订单】result={}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() throws Exception{
        PageRequest request = new PageRequest(0,2);
        Page<OrderDto> orderDtoPage = orderService.findList(BUYEROPENID,request);
        Assert.assertNotEquals(0,orderDtoPage.getTotalElements());
    }

    @Test
    public void cancel() throws Exception{
        OrderDto orderDto = orderService.findOne("1556886740850668457");
        OrderDto result = orderService.cancel(orderDto);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() throws Exception{
        OrderDto orderDto = orderService.findOne("1556890750181681108");
        OrderDto result = orderService.finish(orderDto);
        Assert.assertEquals(OrderStatusEnum.FINISH.getCode(),result.getOrderStatus());
    }

    @Test
    public void paid() throws Exception{
        OrderDto orderDto = orderService.findOne("1556890750181681108");
        OrderDto result = orderService.paid(orderDto);
        Assert.assertEquals(PayStatusEnum.FINISH.getCode(),result.getOrderStatus());
    }

}