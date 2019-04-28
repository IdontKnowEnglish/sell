package com.yu.sell.enums;

import lombok.Getter;

/**
 * @Author: yushizhang
 * @Date: 2019/4/28 21:25
 * @Version 1.0
 */
@Getter
public enum  OrderStatusEnum {
    /**
     * 新订单
     */
    NEW(0,"新订单"),
    /**
     * 完结订单
     */
    FINISH(1,"完结"),
    /**
     * 取消订单
     */
    CANCEL(2,"已取消")

    ;
    private Integer code;
    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
