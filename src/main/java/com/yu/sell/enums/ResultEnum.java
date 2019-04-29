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
    ;
    private Integer code;

    private String msg;

    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
