package com.yu.sell.controller;

import com.yu.sell.converter.OrderForm2OrderDTOConverter;
import com.yu.sell.dto.OrderDto;
import com.yu.sell.enums.ResultEnum;
import com.yu.sell.exception.SellException;
import com.yu.sell.form.OrderForm;
import com.yu.sell.service.OrderService;
import com.yu.sell.utils.ResultVOUtil;
import com.yu.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yushizhang
 * @Date: 2019/5/19 13:06
 * @Version 1.0
 */
@RestController
@RequestMapping("buyer/order")
@Slf4j
public class BuyerOrderController {
    @Autowired
    private OrderService orderService;
    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>> create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确，orderForm={}",orderForm);
            throw new SellException(ResultEnum.PAPAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDto orderDto = OrderForm2OrderDTOConverter.covert(orderForm);
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())){
            log.error("【创建订单】购物车为空");
            throw new SellException(ResultEnum.CAET_EMPTY);
        }
        OrderDto createResult = orderService.create(orderDto);

        Map<String,String> map = new HashMap<>();
        map.put("orderId",createResult.getOrderId());

        return ResultVOUtil.success(map);
    }

    //订单列表

    //订单详情

    //取消订单


}
