package com.yu.sell.enums;

import lombok.Getter;

/**
 * @Author: yushizhang
 * @Date: 2019/4/28 21:31
 * @Version 1.0
 */
@Getter
public enum PayStatusEnum {
    /**
     * 未支付
     */
    WAIT(0,"等待支付"),
    /**
     * 已支付
     */
    FINISH(1,"支付成功"),
    ;

    private Integer code;

    private String msg;


    PayStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }}
