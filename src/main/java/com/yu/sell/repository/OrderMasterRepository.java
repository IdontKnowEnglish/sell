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

    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
