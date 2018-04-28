package com.example.demo.service;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.service
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/28 14:44
 */
public interface StringRedisService {

    String get(String key);

    String hget(String key,String field);

    String set(String key,String value);

    String hset(String key,String field,String value);

}
