package com.yu.sell.vo;

import lombok.Data;

/**
 * http请求返回的最外层对象
 * @Author: yushizhang
 * @Date: 2019/4/25 20:13
 * @Version 1.0
 */
@Data
public class ResultVO <T>{

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 返回的具体内容
     */
    private T data;
}
