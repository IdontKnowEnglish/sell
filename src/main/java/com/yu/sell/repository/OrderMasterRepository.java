package com.yu.sell.repository;

import com.yu.sell.dataobject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: yushizhang
 * @Date: 2019/4/28 21:48
 * @Version 1.0
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,Integer> {

    /**
     * 查询一个用户所有订单
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);

    /**
     * 查询单个订单
     * @param orderId
     * @return
     */
    OrderMaster findByOrderId(String orderId);

}
