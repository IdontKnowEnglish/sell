package com.yu.sell.dto;

import lombok.Data;

/**
 * 购物车
 * @Author: yushizhang
 * @Date: 2019/5/3 19:21
 * @Version 1.0
 */
@Data
public class CartDto {
    /**
     * 产品id
     */
    private String productId;

    /**
     * 数量
     */
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
