package com.example.demo.controller;

import com.example.demo.domain.Result;
import com.example.demo.util.BaiduAPIUtil;
import com.example.demo.util.FileUploadUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Title: demo
 * @Package com.example.demo.controller
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/3/14 12:29
 */
@RestController
public class ImageRecogController {

    @Value("${baiduAPI.imageRecog.appId}")
    private String appId;

    @Value("${baiduAPI.imageRecog.apiKey}")
    private String apiKey;

    @Value("${baiduAPI.imageRecog.secretKey}")
    private String secretKey;

    @Value("${upload.tmpSavePath}")
    private String tmpSavePath;

    @Autowired
    private FileUploadUtil fileUploadUtil;

    private static Logger logger = LoggerFactory.getLogger(OcrController.class);

    @PostMapping("carRecog")
    @ResponseBody
    public Result getImageRecog(@RequestParam("myImage") MultipartFile file)throws Exception{
        // 获取文件名
        String orgFileName = file.getOriginalFilename();
        // 保存上传图片
        fileUploadUtil.saveUploadImage(file,orgFileName,tmpSavePath);
        // 调用百度通用印刷体识别接口
        return BaiduAPIUtil.getInstance().getCarRecog(appId,apiKey,secretKey,tmpSavePath,orgFileName);
    }


}
