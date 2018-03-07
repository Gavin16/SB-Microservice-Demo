package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
 * @Title: demo
 * @Package com.example.demo.util
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/3/5 13:53
 */
public class QcloudUtil {

    private  RestTemplate restTemplate = new RestTemplate();

    public static QcloudUtil getInstance(){
        return new QcloudUtil();
    }

    /**
     * 使用Spring RestTemplate 方式调用接口
     * @param appid
     * @param secretId
     * @param secretKey
     * @param bucketName
     * @param imagePath
     * @param imageFileName
     * @return
     * @throws Exception
     */
    public  Result postForOCR(Long appid,String secretId,String secretKey,String bucketName,String imagePath,String imageFileName)throws Exception{
        // 设置http header信息
        String url = "http://recognition.image.myqcloud.com/ocr/general";
        HttpHeaders requestHeaders = new HttpHeaders();
//        requestHeaders.set("head","xxx");
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        requestHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
//        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        String authorization = getSign(appid,secretId,secretKey,bucketName,1000);
        requestHeaders.set("Authorization",authorization);

        // 添加请求内容 appId, bucket, image
        Map<String,Object> map = new HashMap<>();
        map.put("appid",appid);
        map.put("bucket",bucketName);
        map.put("url","https://mc.qcloudimg.com/static/img/42c0b2c4f07d3815475e02d2201c9902/image.png");

        Object param = JSONObject.toJSON(map);

        HttpEntity request = new HttpEntity (JSONObject.toJSONString(param),requestHeaders);
        // 调 OCR接口
//        HttpEntity<String> resp = restTemplate.exchange(url, HttpMethod.POST,request,String.class);
        String response =  restTemplate.postForObject(url,request,String.class);
        return ResultUtil.success(ExceptionEnum.SUCCESS,response);
    }


    /**
     * 生成调用授权码
     * 生成方式参考 https://cloud.tencent.com/document/product/641/12409
     * @param appId
     * @param secretId
     * @param secretKey
     * @param bucketName
     * @param expired
     * @return
     */
    private String getSign(long appId, String secretId, String secretKey, String bucketName,
                           long expired)throws Exception{
        long now = System.currentTimeMillis() / 1000;
        int rdm = Math.abs(new Random().nextInt());
        String plainText = String.format("a=%d&b=%s&k=%s&t=%d&e=%d&r=%d", appId, bucketName,
                secretId, now, now + expired, rdm);
        byte[] hmacDigest = HmacSha1(plainText, secretKey);

        byte[] signContent = new byte[hmacDigest.length + plainText.getBytes().length];
        System.arraycopy(hmacDigest, 0, signContent, 0, hmacDigest.length);
        System.arraycopy(plainText.getBytes(), 0, signContent, hmacDigest.length,
                plainText.getBytes().length);
        return Base64Encode(signContent);
    }

    /**
     * @param binaryData
     * @return
     */
    private String Base64Encode(byte[] binaryData) {
        String encodedstr = Base64.getEncoder().encodeToString(binaryData);
        return encodedstr;
    }

    /**
     * @param binaryData
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] HmacSha1(byte[] binaryData, String key) throws Exception {
        Mac mac = Mac.getInstance("HmacSHA1");
        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(), "HmacSHA1");
        mac.init(secretKey);
        byte[] HmacSha1Digest = mac.doFinal(binaryData);
        return HmacSha1Digest;
    }

    /**
     * @param plainText
     * @param key
     * @return
     * @throws Exception
     */
    private byte[] HmacSha1(String plainText, String key) throws Exception {
        return HmacSha1(plainText.getBytes(), key);
    }

}
