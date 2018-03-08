package com.example.demo.util;

import com.baidu.aip.ocr.AipOcr;
import com.example.demo.domain.Result;
import com.example.demo.enums.ExceptionEnum;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;

/**
 * @Title: demo
 * @Package com.example.demo.util
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/3/8 15:15
 */
public class BaiduAPIUtil {

    public static Logger logger =  LoggerFactory.getLogger(BaiduAPIUtil.class);

    public static BaiduAPIUtil getInstance(){
        return new BaiduAPIUtil();
    }

    /**
     * 传入待识别文件的路径 调用百度提供的接口
     * @param imagePath
     * @param fileName
     * @return
     */
    public Result getOcrResult(String appId,String apiKey,String secretKey,String imagePath,String fileName){
        HashMap<String,String> param = new HashMap<>();
        param.put("detect_direction","true");
        param.put("probability","true");

        AipOcr client = new AipOcr(appId,apiKey,secretKey);
        JSONObject res = client.basicAccurateGeneral(imagePath+fileName ,param);

        Result result = getRecogResult(res);
        logger.info(res.toString());

        return result;
    }


    /**
     * 调用结果转化为文本输出
     *     结果结构：
     *          log_id
     *          words_result
     *              |-probability
     *                  |-average
     *                  |-min
     *                  |-variance
     *              |-words
     *          words_result_num
     *          direction
     *
     * @param response
     * @return
     */
    private Result getRecogResult(JSONObject response){
        Object obj = response.get("words_result");
        String jsonStr = obj.toString();
        JSONArray array = new JSONArray(jsonStr);

        if(array.length() == 0){
            return ResultUtil.error(103,"json数据未获取到");
        }

        JSONObject jsonObject = null ;
        String finalStr = "";
        for(int i=0;i<array.length();i++){
            jsonObject = array.getJSONObject(i);
            String value = jsonObject.getString("words");
            finalStr = finalStr + value+ ((i == array.length()-1)?"":"\n");
        }

        logger.info("识别结果为："+finalStr);

        return ResultUtil.success(ExceptionEnum.SUCCESS,finalStr);
    }
}
