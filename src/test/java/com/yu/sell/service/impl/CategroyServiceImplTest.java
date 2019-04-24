package com.yu.sell.service.impl;

import com.yu.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategroyServiceImplTest {

    @Autowired
    private CategroyServiceImpl categroyService;

    @Test
    public void findOne() throws Exception{
        ProductCategory productCategory = categroyService.findOne(1);
        Assert.assertEquals(new Integer (1), productCategory.getCategoryId());
    }

    @Test
    public void findAll() throws Exception {
        List<ProductCategory> productCategoryList = categroyService.findAll();
        Assert.assertEquals(3,productCategoryList.size());
    }

    @Test
    public void findByCategoryTypeIn() throws Exception{
        List<ProductCategory> productCategoryList = categroyService.findByCategoryTypeIn(Arrays.asList(2,3));
        Assert.assertEquals(2,productCategoryList.size());
    }

    @Test
    public void save() throws Exception {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryName("豪华跑车");
        productCategory.setCategoryType(10);
        ProductCategory productCategoryResult = categroyService.save(productCategory);
        Assert.assertNotNull(productCategoryResult);
    }
}