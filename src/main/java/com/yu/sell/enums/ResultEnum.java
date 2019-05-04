package com.yu.sell.enums;

import lombok.Getter;

/**
 * @Author: yushizhang
 * @Date: 2019/4/29 22:45
 * @Version 1.0
 */
@Getter
public enum  ResultEnum {

    /**
     * 商品不存在
     */
    PRODUCT_NOT_EXIST(10,"商品不存在"),

    PRIDUCT_STOCK_ERROR(11,"库存不足"),

    ORDER_NOT_EXIST(12,"订单不存在"),

    ORDERDETAIL_NOT_EXIST(13,"订单详情不存在"),

    ORDER_STATUS_ERROR(14,"订单状态不正确"),

    ORDER_UPDATE_ERROR(15,"更新订单状态失败"),

    ORDER_DETAIL_EMPTY(16,"订单详情为空"),
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
