package com.yu.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yu.sell.dataobject.OrderDetail;
import com.yu.sell.dto.OrderDto;
import com.yu.sell.enums.ResultEnum;
import com.yu.sell.exception.SellException;
import com.yu.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yushizhang
 * @Date: 2019/5/19 16:45
 * @Version 1.0
 */
@Slf4j
public class OrderForm2OrderDTOConverter {
    public static OrderDto covert(OrderForm orderForm){
        Gson gson = new Gson();

        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        orderDto.setBuyerOpenid(orderForm.getOpenid());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            orderDetailList = gson.fromJson(orderForm.getItems(),new TypeToken<OrderDetail>(){}.getType());
        }catch (Exception e){
          log.error("【对象转换】错误，string={}",orderForm.getItems());
          throw new SellException(ResultEnum.PAPAM_ERROR);
        }

        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
