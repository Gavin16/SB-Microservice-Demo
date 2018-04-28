package com.example.demo.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);
    /** redis配置*/
    private static final String  HOST = "47.106.148.168";
    private static final Integer PORT = 6379;
    private static final Integer MAX_WAIT = 5000;
    private static final Integer TIME_OUT = 5000;
    private static final Integer MAX_ACTIVE = 100;
    private static final Integer MAX_IDLE = 20;
    private static final Boolean TEST_ON_BORROW = true;
    private static final Integer EXPIRE_TIME = 1800;

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
    private static synchronized Jedis getJedis(){
        try {
            if(null!= pool){
                return pool.getResource();
            }
        } catch (Exception e) {
            logger.error("获取redis连接异常：{}",e);
        }
        return null;
    }

    public static RedisUtil getInstance(){
        return new RedisUtil();
    }

    public String get(String key){
        Jedis jedis = getJedis();
        if(null!=jedis){
            try {
                String value = jedis.get(key);
                return value;
            } catch (Exception e) {
                logger.error("get()方法获取{}的值异常:{}",key,e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public String hget(String key,String field){
        Jedis jedis = getJedis();
        if(null!=jedis){
            try {
                String value = jedis.hget(key,field);
                return value;
            } catch (Exception e) {
                logger.error("get()方法获取{}-{}的值异常:{}",key,field,e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }


    public Long set(String key,String value){
        Jedis jedis = getJedis();
        if(null!=jedis){
            try {
                String res = jedis.set(key,value);
                if("ok".equalsIgnoreCase(res)){
                    return expire(jedis,key,EXPIRE_TIME);
                }
            } catch (Exception e) {
                logger.error("set()方法设置{}:{}异常{}",key,value,e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public Long hset(String key,String field,String value){
        Jedis jedis = getJedis();
        if(null!=jedis){
            try {
                Long res = jedis.hset(key, field, value);
                if(res > 0){
                    return expire(jedis,key,EXPIRE_TIME);
                }
            } catch (Exception e) {
                logger.error("hset()方法设置{}:{}:{}异常{}",key,field,value,e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public Long expire(Jedis jedis,String key,Integer expireTime){
        return jedis.expire(key,expireTime);
    }

}
