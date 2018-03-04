package com.example.demo.enums;

/**
 * @Title: SBService
 * @Package com.demo.enums
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/2/27 14:44
 */
public enum ExceptionEnum {
    SUCCESS(100,"调用成功"),
    PARAM_NULL_ERROR(200,"参数为空"),
    UNKNOW_ERROR(-1,"未知错误"),
    ;

    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    ExceptionEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }
}
