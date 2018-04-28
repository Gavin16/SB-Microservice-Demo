package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.config
 * @Description:
 * @author: eta
 * @date 2018/4/28 10:38
 */
@Configuration
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String hostName;

    @Value("${spring.redis.pool.max-active}")
    private Integer maxActive;

    @Value("${spring.redis.pool.max-wait}")
    private Integer maxWait;

    @Value("${spring.redis.pool.max-idle}")
    private Integer maxIdle;

    @Value("${spring.redis.port}")
    private Integer port;

    @Value("${spring.redis.timeout}")
    private Integer timeOut;

    /** 考虑在此处是否可以注入JedisPoolConfig */
//    @Autowired
//    private JedisPoolConfig poolConfig;

    @Bean
    public JedisPoolConfig getPoolConfig(){
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setTestOnBorrow(true);
        return poolConfig;
    }

    @Bean
    public JedisConnectionFactory getFactory(){
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(hostName);
        factory.setPort(port);
        factory.setTimeout(timeOut);
        factory.setPoolConfig(getPoolConfig());
        return factory;
    }

    @Bean
    public StringRedisTemplate getTemplate(){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(getFactory());
        template.afterPropertiesSet();
        return template;
    }
}
