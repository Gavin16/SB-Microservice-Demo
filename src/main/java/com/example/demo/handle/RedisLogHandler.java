package com.example.demo.handle;

import com.example.demo.service.impl.StringRedisServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.handle
 * @Description: 调用StringRedisService 的日志统一由 RedisLogHandler 处理
 * @author: eta
 * @date 2018/4/28 14:49
 */
@Component
public class RedisLogHandler implements InvocationHandler{

    Logger logger = LoggerFactory.getLogger(RedisLogHandler.class);

    @Autowired
    StringRedisServiceImpl stringRedisService;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if(logger.isInfoEnabled()){
            // 获取方法的传参打印


            Parameter[] parameters = method.getParameters();
            List<Parameter> paramList = Arrays.asList(parameters);
        }
        Object result = method.invoke(stringRedisService,args);

        return result;
    }
}
