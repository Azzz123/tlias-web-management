package com.azhuo.controller;

import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Result;
import com.azhuo.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private final ClazzService clazzService;
    public ClazzController(ClazzService clazzService) {
        this.clazzService = clazzService;
    }
    /**
     * 分页查询班级
     */
     @GetMapping
     public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("分页查询班级，参数：{}", clazzQueryParam);
        // 调用服务层方法查询分页结果
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        // 返回分页结果
        return Result.success(pageResult);
    }

}
