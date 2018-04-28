package com.example.demo.handle;

import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;
import com.example.demo.exceptions.DescribeException;
import com.example.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title: SBService
 * @Package: com.demo.handle
 * @Description: 项目抛出的指定类型的异常都会经过ExceptionHandle处理,处理返回Result类型的结果
 * @author: Minsky
 * @date 2018/2/27 15:35
 *  使用ControllerAdvice注解标注的类下定义的有 @ExceptionHandler方法会作用在含@RequestMapping注解的方法上
 *
 */
@ControllerAdvice
public class ExceptionHandle {

    public static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    /**
     * 已知错误：返回DescribeExcption的code和message
     * 未知错误：统一返回 预先定义好的code和message
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result getException(Exception e){
        if(e instanceof DescribeException){
            DescribeException describeException = (DescribeException)e;
            return ResultUtil.error(describeException.getCode(),describeException.getMessage());
        }
        logger.error("【系统异常】:",e);
        return ResultUtil.error(ExceptionEnum.UNKNOW_ERROR);
    }
}
