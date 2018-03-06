package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Person;
import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;
import com.example.demo.util.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;

/**
 * @Title: SB-Microservice-Demo
 * @Description: 接收传入的图片文件
 * (1) 文件保存路径设置
 * (2) 文件大小限制设置  application.yml文件中配置
 * (3) 文件格式问题
 * @author: 80002748
 * @date 2018/3/2 14:43
 */

@RestController
public class OcrController {

    @Value("${appid}")
    private Long appid;

    @Value("${secretId}")
    private  String secretId;

    @Value("${secretKey}")
    private  String secretKey;

    @Value("${bucketName}")
    private  String bucketName;

    private RestTemplate restTemplate = new RestTemplate();

    private static Logger logger = LoggerFactory.getLogger(OcrController.class);
    /**
     * 接收调用者的图片传参,并使用spring restTemplate 调用 qcloud OCR接口
     * 涉及：IO流,文件系统,服务器部署相关,RESTclient 文件传参
     *
     * springmvc中文手册：http://download.csdn.net/download/zhang_hongli_li/9799725
     * @throws Exception
     * @return Result
     */
    @PostMapping(value = "charRecog")
    public Result generalCharRecongnize(@RequestParam("myImage") MultipartFile file) throws Exception {
        if(file.isEmpty()){
            logger.error("上传文件为空");
            return ResultUtil.error(400,"上传文件为空");
        }
        String orgFileName = file.getOriginalFilename();
        String extendName = StringUtils.substringAfter(orgFileName,".");
        logger.info("原始文件名为："+orgFileName);

        // 获取项目部署路径
        String systemPath = System.getProperty("webapp.root");
        logger.info("系统路径为："+systemPath);

        // 文件直接保存在yml文件配置的目录下
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(orgFileName)));
        out.write(file.getBytes());
        out.flush();
        out.close();

        // 读取上传文件
        FileSystemResource resource = new FileSystemResource(systemPath + orgFileName);

        logger.info("读取文件名为："+resource.getFilename());

        // 新文件名
//        String newFileName = System.currentTimeMillis()+"."+extendName;
        // 获取项目根路径
//        String systemPath = System.getProperty("webapp.root");
//        System.out.println("系统路径为："+systemPath);
//        String newFilePath = "/uploaded/image/";
//        File newFile = new File(newFilePath,newFileName);
//        file.transferTo(newFile);

        return ResultUtil.success(ExceptionEnum.SUCCESS,"文件保存成功");
//        return QcloudUtil.getInstance().postForOCR(appid,secretId,secretKey,bucketName);
    }

    /**
     * 若同样的方式 addPersonTest方法可以调通,则generalCharRecongnize方法 bad request是由于参数不全导致
     * @return
     */
    @GetMapping(value = "testRestTemplate")
    public Result addPersonTest(){
        Person person = new Person();
        person.setName("Harden");
        person.setAge(29);
        person.setNation("USA");
        person.setTel("782340213");
        person.setGender("male");

        String url = "http://127.0.0.1:8081/springboot/addPerson";
        HttpHeaders header = new HttpHeaders();
        header.setContentType(MediaType.APPLICATION_JSON);
        header.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        Object obj = JSONObject.toJSON(person);
        HttpEntity<?> entity = new HttpEntity(obj,header);

        ResponseEntity<String>response =  restTemplate.exchange(url, HttpMethod.POST,entity,String.class);

        // 考虑从JSONObject 中获取出来的对象怎么转化为 制定DTO
        String jsonBody = response.getBody();
        JSONObject jsonObject = JSONObject.parseObject(jsonBody);
        // 使用JSON.parseObject 方法可以将 String形式的对象转化为 DTO
        Person p = JSON.parseObject(jsonObject.get("data").toString(),Person.class);
        return ResultUtil.success(ExceptionEnum.SUCCESS,p);
    }

}
