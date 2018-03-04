package com.example.demo.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @Title: ${FILE_NAME}
 * @Package: com.demo.controller
 * @Description:
 * @author: Minsky
 * @date: 2018/3/4 7:51
 */
@RestController
public class FileUploadController {


    @PostMapping(value = "/uploadFile",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String handleFileUpload(@RequestParam(value = "myImage") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        String fileName = file.getName();
        System.out.println(fileName);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";
    }

}

