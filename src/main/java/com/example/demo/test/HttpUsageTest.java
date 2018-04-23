package com.example.demo.test;

import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.test
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/23 16:44
 */
public class HttpUsageTest {

    /**
     * 不增加依赖,原生的调用方式
     */
    public boolean test01(String u,String jsonStr){
        try {
            /** 定义一个URL对象*/
            URL url = new URL(u);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

            /** 设置请求方式 */
            httpURLConnection.setRequestMethod("POST");
            /**  将doOutput标志设置为true表示应用程序打算将数据写入URL连接*/
            httpURLConnection.setDoOutput(true);
            /** 连接超时时间设置为 1000ms*/
            httpURLConnection.setConnectTimeout(5000);
            /** 请求头信息设置*/
            httpURLConnection.setRequestProperty("Accept-Charset","utf-8");
            httpURLConnection.setRequestProperty("Content-Type","application/json");

            /** 传参数使用json格式的字符串,*/
            String param = getJsonString();
            byte[] bytes = param.toString().getBytes();

            /** 在这里传入POST请求的参数,write()方法写入参数*/
            httpURLConnection.getOutputStream().write(bytes);

            /** 打印是否连接成功*/
            System.out.println(httpURLConnection.getResponseCode());

            /** 查看调用结果*/
            byte results[]=new byte[1024];
            InputStream is = httpURLConnection.getInputStream();
            is.read(results,0,is.available());
            System.out.println(new String(results,"UTF-8"));

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[]args) {
        HttpUsageTest usageTest = new HttpUsageTest();
        String url = "";
        String jsonStr = usageTest.getJsonString();
        boolean relt = usageTest.test01(url,jsonStr);
        System.out.println(relt);
    }

    // 获取json字符串
    private String getJsonString(){
        Map<String,String> params = new HashMap<>();
        params.put("","");
        params.put("","");
        params.put("","");
        String jsonStr = JSONObject.toJSONString(params);
        return jsonStr;
    }

}
