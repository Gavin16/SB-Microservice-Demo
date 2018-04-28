package com.example.demo.test;

import com.example.demo.util.RedisUtil;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.test
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/27 15:44
 */
public class RedisTest {

    public static void main(String[]args){
        RedisUtil redisUtil = RedisUtil.getInstance();
        redisUtil.hset("user","name","Tom");
        redisUtil.hset("user","password","123456");
        redisUtil.hset("user","openid","321423742868");
        System.out.println(redisUtil.hget("user","openid"));
    }
}
