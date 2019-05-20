package com.yu.sell.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yu.sell.dataobject.OrderDetail;
import com.yu.sell.utils.serializer.Date2LongSerializer;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/4/29 22:29
 * @Version 1.0
 */
@Data
//@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
public class OrderDto {
    /**
     * 订单id
     */
    private String orderId;

    /**
     * 买家姓名
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态  默认为新下单0
     */
    private Integer orderStatus;

    /**
     * 支付状态 默认为等待支付0
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    /**
     * 订单详情列表
     */
    List<OrderDetail> orderDetailList;

}
