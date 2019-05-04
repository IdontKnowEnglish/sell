package com.yu.sell.converter;

import com.yu.sell.dataobject.OrderMaster;
import com.yu.sell.dto.OrderDto;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: yushizhang
 * @Date: 2019/5/4 13:33
 * @Version 1.0
 */
public class OrderMaster2OrderDtoConverter {

    public static OrderDto convert(OrderMaster orderMaster){
        OrderDto orderDto = new OrderDto();

        BeanUtils.copyProperties(orderMaster,orderDto);

        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster>orderMasterList){
        List<OrderDto> orderDtoList = orderMasterList.stream().map(orderMaster ->
                convert(orderMaster)
                ).collect(Collectors.toList());
        return orderDtoList;
    }
}
