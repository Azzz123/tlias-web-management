package com.azhuo.controller;

import com.azhuo.pojo.OperateLog;
import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Result;
import com.azhuo.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志控制器
 */
@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {
    @Autowired
    private final OperateLogService operateLogService;
    public OperateLogController(OperateLogService operateLogService) {
        this.operateLogService = operateLogService;
    }
    /**
     * 分页查询操作日志
     */
    @GetMapping("/page")
    public Result page(Integer page, Integer pageSize) {
        log.info("分页查询操作日志");
        // 调用服务层方法查询分页结果
        PageResult<OperateLog> pageResult = operateLogService.page(page, pageSize);
        // 返回分页结果
        return Result.success(pageResult);
    }
}
