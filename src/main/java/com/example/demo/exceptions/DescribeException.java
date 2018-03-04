package com.example.demo.exceptions;


import com.example.demo.enums.ExceptionEnum;

/**
 * @Title: SBService
 * @Package com.demo.handle
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/2/27 15:14
 */
public class DescribeException extends RuntimeException {

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public DescribeException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
    }

    public DescribeException(String msg, Integer code) {
        super(msg);
        this.code = code;
    }
}
