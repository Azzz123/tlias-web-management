package com.azhuo.controller;

import com.azhuo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result upload(String name, Integer age, MultipartFile file) {
        log.info("上传文件, 接收参数name={}, age={}, file={}", name, age, file);
        return Result.success();
    }
}
