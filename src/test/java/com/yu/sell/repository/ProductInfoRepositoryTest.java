package com.yu.sell.repository;

import com.yu.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/4/24 22:15
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository productInfoRepository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo();
        productInfo.setProductId("123456");
        productInfo.setProductName("法拉利");
        productInfo.setProductPrice(new BigDecimal(1234567.88));
        productInfo.setProductStock(10);
        productInfo.setProductDescription("豪华轿跑法拉利");
        productInfo.setProductIcon("http://xxxxx.jpg");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(10);

        ProductInfo productInfoResult = productInfoRepository.save(productInfo);
        Assert.assertNotNull(productInfoResult);
    }
    @Test
    public void findByProductStatus() throws Exception{
        List<ProductInfo> productInfoList = productInfoRepository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}