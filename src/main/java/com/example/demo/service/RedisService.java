package com.example.demo.service;

import com.example.demo.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.service
 * @Description:  使用RedisUtil访问redis数据库
 * @author: eta
 * @date 2018/4/27 15:09
 */
@Service
public class RedisService {

    private Logger logger = LoggerFactory.getLogger(RedisService.class);

    public String get(String key){
        Jedis jedis = RedisUtil.getJedis();
        if(null != jedis){
            try {
                String value = jedis.get(key);
                return value;
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public String hget(String key,String field){
        Jedis jedis = RedisUtil.getJedis();
        if(null != jedis){
            try {
                String value = jedis.hget(key,field);
                return value;
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public String set(String key,String value){
        Jedis jedis = RedisUtil.getJedis();
        if(null != jedis){
            try {
                String result = jedis.set(key,value);
                return result;
            } catch (Exception e) {
                logger.error("RedisService 设置key-value失败：{}",e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public Long set(String key,String value,Integer expireTime){
        Jedis jedis = RedisUtil.getJedis();
        if(null != jedis){
            try {
                String result = jedis.set(key,value);
                if ("OK".equalsIgnoreCase(result) && expireTime >= 0){
                    Long exResult = jedis.expire(key,expireTime);
                    return exResult;
                }
            } catch (Exception e) {
                logger.error("RedisService 设置key-value 并指定过期时间失败：{}",e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public Long hset(String key,String field,String value){
        Jedis jedis = RedisUtil.getJedis();
        if(null != jedis){
            try {
                Long result = jedis.hset(key,field,value);
                return result;
            } catch (Exception e) {
                logger.error("RedisService 设置hash key-field失败：{}",e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    public Long hset(String key,String field,String value,Integer expireTime){
        Jedis jedis = RedisUtil.getJedis();
        if(null != jedis){
            try {
                Long hsetResult = jedis.hset(key,field,value);
                Long exResult = jedis.expire(key,expireTime);
                if(hsetResult > 0 && exResult > 0){
                    return exResult;
                }
            } catch (Exception e) {
                logger.error("RedisService 设置hash key-field失败：{}",e);
            } finally {
                jedis.close();
            }
        }
        return null;
    }

}
