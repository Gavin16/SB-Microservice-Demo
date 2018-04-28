package com.example.demo.service.impl;

import com.example.demo.service.StringRedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.service
 * @Description: 提供对StringRedisTemplate的封装
 * @author: eta
 * @date 2018/4/28 13:58
 */
@Component
public class StringRedisServiceImpl implements StringRedisService {

    Logger logger = LoggerFactory.getLogger(StringRedisServiceImpl.class);

    @Autowired
    @Qualifier("getTemplate")
    StringRedisTemplate template;

    @Override
    public String get(String key){
        return null;
    }

    @Override
    public String hget(String key,String field){
        return null;
    }

    @Override
    public String set(String key, String value) {
        return null;
    }

    @Override
    public String hset(String key, String field, String value) {
        return null;
    }
}
