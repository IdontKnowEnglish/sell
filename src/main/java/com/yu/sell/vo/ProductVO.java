package com.yu.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * 商品（包含类目）
 * @Author: yushizhang
 * @Date: 2019/4/25 20:35
 * @Version 1.0
 */
@Data
public class ProductVO {

    /**
     *类目名字
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     *类目类型
     */
    @JsonProperty("type")
    private Integer categoryType;


    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
