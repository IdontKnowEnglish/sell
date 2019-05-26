package com.yu.sell.service;

import com.yu.sell.dto.OrderDto;

/**
 * 买家
 * @Author: yushizhang
 * @Date: 2019/5/21 22:29
 * @Version 1.0
 */
public interface BuyerService {
    //查询一个订单
    OrderDto findOrderOne(String openid,String orderId);
    //取消订单
    OrderDto cancelOrderOne(String openid,String orderId);
}
