package com.example.demo.controller;

import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;
import com.example.demo.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: SB-Service-Demo
 * @Package com.ocr.controller
 * @Description: 接收传入的图片文件
 * (1) 文件保存路径设置
 * (2) 文件大小限制设置  application.yml文件中配置
 * (3) 文件格式问题
 * @author: 80002748
 * @date 2018/3/2 14:43
 */

@RestController
public class OcrController {

    @Autowired
    private RestTemplate  restTemplate;

    @GetMapping(value = "charRecog")
    public Result generalCharRecongnize() throws Exception {

        // 使用spring restTemplate 调用 qcloud OCR接口
        String url = "http://recognition.image.myqcloud.com/ocr/general";
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Map<String,Object> map = new HashMap<>();

        HttpEntity request = new HttpEntity (map,requestHeaders);
        Map<String,Object> resMap = restTemplate.postForObject(url,request,Map.class);

        return ResultUtil.success(ExceptionEnum.SUCCESS,resMap);
    }
}
