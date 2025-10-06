package com.azhuo.controller;

import com.azhuo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping
    public Result upload(String name, Integer age, MultipartFile file) {
        log.info("上传文件, 接收参数name={}, age={}, file={}", name, age, file);

        // ----------------------将上传的文件保存到服务器指定位置----------------------
        // 1. 获取原始文件名
        String originalFilename = file.getOriginalFilename();

        // 2. 新的文件名
        // 2.1 先调用randomUUID()方法，生成一个随机的UUID字符串
        // 2.2 调用toString()方法，将UUID对象转换为字符串
        // 2.3 调用substring()方法，从原始文件名中按照最后一个点号的位置提取文件扩展名
        // 2.4 拼接新的文件名，将随机UUID字符串和文件扩展名拼接起来
        String newFilename =
                UUID.randomUUID()
                +
                originalFilename.substring(originalFilename.lastIndexOf("."));

        // 3. 保存文件到服务器指定位置
        try {
            file.transferTo(new File("D:/Azhuo/Java/upload/" + newFilename));
        } catch (Exception e) {
            log.error("上传文件失败", e);
            return Result.error("上传文件失败");
        }
        return Result.success();
    }
}
