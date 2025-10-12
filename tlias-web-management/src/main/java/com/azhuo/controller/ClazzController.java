package com.azhuo.controller;

import com.azhuo.anno.Log;
import com.azhuo.pojo.*;
import com.azhuo.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * 添加班级
     */
    @PostMapping
    @Log
    public Result save(@RequestBody Clazz clazz) {
        log.info("添加班级，参数：{}", clazz);
        // 调用服务层方法添加班级
        clazzService.save(clazz);
        // 返回成功结果
        return Result.success();
    }

    /**
     * 根据ID查询班级
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询班级，参数：{}", id);
        // 调用服务层方法根据ID查询班级
        Clazz clazz = clazzService.getById(id);
        // 返回班级信息
        return Result.success(clazz);
    }

    /**
     * 修改班级
     */
    @PutMapping
    @Log
    public Result update(@RequestBody Clazz clazz) {
        log.info("修改班级，参数：{}", clazz);
        // 调用服务层方法修改班级
        clazzService.update(clazz);
        // 返回成功结果
        return Result.success();
    }

    /**
     * 根据ID删除班级
     */
    @DeleteMapping("/{id}")
    @Log
    public Result deleteById(@PathVariable Integer id) {
        log.info("根据ID删除班级，参数：{}", id);
        // 调用服务层方法根据ID删除班级
        clazzService.deleteById(id);
        // 返回成功结果
        return Result.success();
    }

    /**
     * 查询所有班级
     */
    @GetMapping("/list")
    public Result getAll() {
        log.info("查询所有班级");
        List<Clazz> clazzList = clazzService.getAll();
        return Result.success(clazzList);
    }

}
