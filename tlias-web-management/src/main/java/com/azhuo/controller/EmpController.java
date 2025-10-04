package com.azhuo.controller;

import com.azhuo.pojo.Emp;
import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Result;
import com.azhuo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    /**
     * 分页查询员工
     */
    // 因为前端传递的是page和pageSize简单参数，所以这里不需要@RequestParam注解，查询一般是GET请求
    @Autowired // 自动注入EmpService Bean
    private final EmpService empService;
    public EmpController(EmpService empService) {
        this.empService = empService;
    }

    @GetMapping
    // 设置默认值page=1, pageSize=10
    // @RequestParam注解用于获取请求参数
    // defaultValue设置默认值只能在Controller层，因为Controller层是处理请求的入口，而Service层是业务逻辑的实现，不应该包含请求相关的代码。
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("分页查询员工，页码：{}，每页数量：{}", page, pageSize);
        // 调用服务层方法查询分页结果
        PageResult<Emp> pageResult = empService.page(page, pageSize);
        // 返回分页结果
        return Result.success(pageResult);
    }

}
