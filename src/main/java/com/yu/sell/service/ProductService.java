package com.yu.sell.service;

import com.yu.sell.dataobject.ProductInfo;
import com.yu.sell.dto.CartDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/4/24 22:26
 * @Version 1.0
 */
public interface ProductService {

    ProductInfo findOne(String productId);

    /**
     * 查询再架商品
     * @return
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    /**
     * 加库存
     */
    void increaseStock(List<CartDto> cartDtoList);


    /**
     * 减库存
     */
    void decreaseStock(List<CartDto>cartDtoList);
}
