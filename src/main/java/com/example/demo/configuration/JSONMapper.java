package com.example.demo.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Component;

/**
 * @Title: demo
 * @Package com.example.demo.configuration
 * @Description: 使用百度 AipOcr 调用文字识别接口时报错:
 *                  Could not write JSON: No serializer found for class org.json.JSONObject and
 *                  增加对SerilizationFeature 的配置
 * @author: Minsky
 * @date 2018/3/8 17:43
 */
public class JSONMapper extends ObjectMapper{

    public JSONMapper(){
        this.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS,false);
    }
}
