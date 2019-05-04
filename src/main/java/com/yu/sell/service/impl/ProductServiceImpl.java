package com.yu.sell.service.impl;

import com.yu.sell.dataobject.ProductInfo;
import com.yu.sell.dto.CartDto;
import com.yu.sell.enums.ProductStatusEnum;
import com.yu.sell.enums.ResultEnum;
import com.yu.sell.exception.SellException;
import com.yu.sell.repository.ProductInfoRepository;
import com.yu.sell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/4/24 22:31
 * @Version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Override
    public ProductInfo findOne(String productId) {
        ProductInfo productInfo  = productInfoRepository.findById(productId).get();
        return productInfo;
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepository.save(productInfo);
    }

    @Override
    public void increaseStock(List<CartDto> cartDtoList) {
        cartDtoList.stream().forEach(cartDto -> {
            ProductInfo productInfo = productInfoRepository.findByProductId(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() + cartDto.getProductQuantity();

            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        });

    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDtoList) {
        cartDtoList.stream().forEach(cartDto -> {
            ProductInfo productInfo = productInfoRepository.findByProductId(cartDto.getProductId());
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if(result < 0){
                throw new SellException(ResultEnum.PRIDUCT_STOCK_ERROR);
            }

            productInfo.setProductStock(result);

            productInfoRepository.save(productInfo);
        });
    }
}
