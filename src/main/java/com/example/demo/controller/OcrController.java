package com.example.demo.controller;

import com.example.demo.domain.Result;
import com.example.demo.util.QcloudUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: SB-Service-Demo
 * @Description: 接收传入的图片文件
 * (1) 文件保存路径设置
 * (2) 文件大小限制设置  application.yml文件中配置
 * (3) 文件格式问题
 * @author: 80002748
 * @date 2018/3/2 14:43
 */

@RestController
public class OcrController {

    /**
     * 接收调用者的图片传参,并使用spring restTemplate 调用 qcloud OCR接口
     * @throws Exception
     * @return Result
     */
    @GetMapping(value = "charRecog")
    public Result generalCharRecongnize() throws Exception {
        return QcloudUtil.getInstance().postForOCR();
    }

}
