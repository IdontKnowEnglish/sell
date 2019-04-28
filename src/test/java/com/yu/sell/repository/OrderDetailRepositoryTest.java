package com.yu.sell.repository;

import com.yu.sell.dataobject.OrderDetail;
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
 * @Date: 2019/4/28 22:33
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("12345678910");
        orderDetail.setOrderId("1111111");
        orderDetail.setProductIcon("http://xxx.jpg");
        orderDetail.setProductId("11111112");
        orderDetail.setProductName("宝骏");
        orderDetail.setProductPrice(new BigDecimal(12345679.12));
        orderDetail.setProductQuantity(2);

        OrderDetail result = orderDetailRepository.save(orderDetail);

        Assert.assertNotNull(result);
    }
    @Test
    public void findByOrderId() throws Exception{
        List<OrderDetail>orderDetailList =orderDetailRepository.findByOrderId("1111111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}