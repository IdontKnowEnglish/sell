package com.yu.sell.exception;

import com.yu.sell.enums.ResultEnum;

/**
 * @Author: yushizhang
 * @Date: 2019/4/29 22:44
 * @Version 1.0
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMsg());

        this.code = resultEnum.getCode();
    }
}
