package com.yu.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 * @Author: yushizhang
 * @Date: 2019/4/24 22:46
 * @Version 1.0
 */
@Getter
public enum ProductStatusEnum {
    /**
     * 商品上架
     */
    UP(0,"上架"),
    /**
     * 商品下架
     */
    DOWN(1,"下架"),
    ;
    private Integer code;

    private String msg;
    ProductStatusEnum(Integer code , String msg){
        this.code = code;
        this.msg = msg;
    }


}
