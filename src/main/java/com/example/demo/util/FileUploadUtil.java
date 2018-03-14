package com.example.demo.util;

import com.example.demo.domain.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @Title: demo
 * @Package com.example.demo.util
 * @Description: ${todo}
 * @author: 80002748
 * @date 2018/3/14 12:34
 */
@Component
public class FileUploadUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUploadUtil.class);

    public Result saveUploadImage(MultipartFile file, String orgFileName,String tmpSavePath) throws Exception{
        if(file.isEmpty()){
            logger.error("上传文件为空");
            return ResultUtil.error(400,"上传文件为空");
        }

        logger.info("原始文件名为："+orgFileName);

        // 获取项目部署路径
        String systemPath = System.getProperty("webapp.root");
        logger.info("系统路径为："+systemPath);

        // 文件直接保存在yml文件配置的目录下
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(new File(tmpSavePath,orgFileName)));
        out.write(file.getBytes());
        out.flush();
        out.close();
        return null;
    }
}
