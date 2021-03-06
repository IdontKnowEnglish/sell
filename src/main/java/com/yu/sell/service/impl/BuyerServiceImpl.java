package com.yu.sell.service.impl;

import com.yu.sell.dto.OrderDto;
import com.yu.sell.enums.ResultEnum;
import com.yu.sell.exception.SellException;
import com.yu.sell.service.BuyerService;
import com.yu.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: yushizhang
 * @Date: 2019/5/21 22:31
 * @Version 1.0
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid,orderId);
    }

    @Override
    public OrderDto cancelOrderOne(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid,orderId);
        if(orderDto == null){
            log.error("【取消订单】查不到该订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid,String orderId){
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null){
            return null;
        }
        //判断是否是自己的订单
        if(orderDto.getBuyerOpenid().equalsIgnoreCase(openid)){
            log.error("【查询订单】订单的openid不一致，openid={},orderDto={}",openid,orderDto);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDto;
    }
}
