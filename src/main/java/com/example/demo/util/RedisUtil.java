package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.util
 * @Description: V1.0
 * @author: eta
 * @date 2018/4/27 14:09
 *      配置写在util静态属性中
 *      
 */
public class RedisUtil {

    private static StringRedisTemplate redisTemplate = new StringRedisTemplate();
    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static final String  HOST = "47.106.148.168";
    private static final Integer PORT = 6379;
    private static final Integer MAX_WAIT = 5000;
    private static final Integer TIME_OUT = 5000;
    private static final Integer MAX_ACTIVE = 100;
    private static final Integer MAX_IDLE = 20;
    private static final Boolean TEST_ON_BORROW = true;

    private static  JedisPool pool = null;

    /** 初始化*/
    static{
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            pool = new JedisPool(config,HOST,PORT,TIME_OUT);
        } catch (Exception e) {
            logger.error("static块中创建JedisPool异常:{}",e);
        }
    }

    /** 若连接池不为null 则返回一个jedis连接 */
    public static synchronized Jedis getJedis(){
        try {
            if(null!= pool){
                return pool.getResource();
            }
        } catch (Exception e) {
            logger.error("获取redis连接异常：{}",e);
        }
        return null;
    }

    public static String get(String key){
        return redisTemplate.opsForValue().get(key);
    }
}
