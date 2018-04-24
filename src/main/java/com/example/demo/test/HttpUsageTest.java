package com.example.demo.test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Title: SB-Microservice-Demo
 * @Package com.example.demo.test
 * @Description: ${todo}
 * @author: eta
 * @date 2018/4/23 16:44
 */
public class HttpUsageTest {

    /**
     * 不增加依赖,使用jdk原生的调用方式
     */
    public String rawMethodToCall(String u,String paramStr){
        String resp = "";
        try {
            /** 定义一个URL对象*/
            URL urlObject = new URL(u);
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlObject.openConnection();

            /** 设置请求方式 */
            httpURLConnection.setRequestMethod("POST");
            /**  将doOutput标志设置为true表示应用程序打算将数据写入URL连接*/
            httpURLConnection.setDoOutput(true);
            /** 连接超时时间设置为 1000ms*/
            httpURLConnection.setConnectTimeout(5000);
            /** 请求头信息设置*/
            httpURLConnection.setRequestProperty("Accept-Charset","utf-8");
            httpURLConnection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

            /** 传参数使用json格式的字符串,*/
            byte[] bytes = paramStr.getBytes();

            /** 在这里传入POST请求的参数,write()方法写入参数*/
            DataOutputStream dos = new DataOutputStream(httpURLConnection.getOutputStream());
            dos.write(bytes);
            dos.flush();

            /** 打印是否连接成功*/
            System.out.println(httpURLConnection.getResponseCode());

            /** 获取调用结果,这里只有当返回结果数据量较小时才使用固定1024字节长度*/
            byte results[]=new byte[1024];
            InputStream is = httpURLConnection.getInputStream();
            is.read(results,0,is.available());
            resp = new String(results,"UTF-8");
            dos.close();
            is.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resp;
    }

    /**
     * 使用 Spring封装的 template调用
     * @return
     */
    public String useTemplate(){
        return null;
    }

    /**
     * 使用httpClient调用
     * @return
     */
    public  String useHttpClient(){
        return null;
    }

    public static void main(String[]args) {
        HttpUsageTest usageTest = new HttpUsageTest();
        String url = "http://msky.s1.natapp.cc/sell/buyer/order/list";
        String jsonStr = usageTest.getJsonString();
        String relt = usageTest.rawMethodToCall(url,jsonStr);
        System.out.println(relt);
    }

    // 获取json字符串
    private String getJsonString(){
        // application/json 格式数据传参
//        Map<String,String> params = new HashMap<>();
//        params.put("openid","ew3euwhd7sjw9diwkq");
//        params.put("page","1");
//        params.put("size","3");
//        String jsonStr = JSONObject.toJSONString(params);
        // x-www-form-urlencoded 格式数据

        StringBuilder params = new StringBuilder();
        params.append("openid=").append("ew3euwhd7sjw9diwkq")
                .append("&page=").append("1")
                .append("&size=").append("3");
        return params.toString();
    }

}
