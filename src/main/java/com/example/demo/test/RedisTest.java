package com.example.demo.test;

import com.example.demo.service.RedisService;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.test
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/27 15:44
 */
public class RedisTest {

    static RedisService service = new RedisService();

    public static void main(String[]args){
        service.hset("user","name","admin");
        String name = service.hget("user","name");
        System.out.println(name);
    }
}
