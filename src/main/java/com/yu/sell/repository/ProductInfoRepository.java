package com.yu.sell.repository;

import com.yu.sell.dataobject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/4/24 22:10
 * @Version 1.0
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String>{

    /**
     * @Author: yushizhang
     * @param productStatus
     * @return
     * 查询商品根据状态
     */
    List<ProductInfo> findByProductStatus(Integer productStatus);

    ProductInfo findByProductId(String productId);
}
