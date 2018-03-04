package com.example.demo.util;


import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;

/**
 * @Title: SBService
 * @Package com.demo.util
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/2/27 14:24
 */
public class ResultUtil {

    /**
     * 调用成功
     * @param object
     * @return
     */
    public static Result success(ExceptionEnum exceptionEnum, Object object){
        Result res = new Result();
        res.setStatus(exceptionEnum.getCode());
        res.setMsg(exceptionEnum.getMsg());
        res.setData(object);
        return res;
    }

    /**
     * 部分调用无需返回数据
     * @param exceptionEnum
     * @return
     */
    public static Result success(ExceptionEnum exceptionEnum){
        return success(exceptionEnum,null);
    }


    /**
     * 调用错误
     * @param
     * @return
     */
    public static Result error(ExceptionEnum exceptionEnum){
        Result res = new Result();
        res.setStatus(exceptionEnum.getCode());
        res.setMsg(exceptionEnum.getMsg());
        res.setData(null);
        return res;
    }


    /**
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code,String msg){
        Result res = new Result();
        res.setStatus(code);
        res.setMsg(msg);
        res.setData(null);
        return res;
    }


}
