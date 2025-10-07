package com.azhuo.controller;

import com.azhuo.pojo.Emp;
import com.azhuo.pojo.EmpQueryParam;
import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Result;
import com.azhuo.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


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
    // 参数过多时可以封装为对象，方便传递和管理，同时也可以避免参数顺序错误的问题。spring会自动将请求参数绑定到对象的属性上。
    public Result page(EmpQueryParam empQueryParam) {
        log.info("分页查询员工，参数：{}", empQueryParam);
        // 调用服务层方法查询分页结果
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        // 返回分页结果
        return Result.success(pageResult);
    }

    /**
     * 新增员工
     */
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        // 日志记录
        log.info("新增员工，参数：{}", emp);
        // 调用服务层方法保存员工
        empService.save(emp);
        // 返回成功结果
        return Result.success();
    }

    /**
     * 批量删除员工
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("删除员工，参数：{}", Collections.singletonList(ids));
        // 调用服务层方法删除员工
        empService.delete(ids);
        // 返回成功结果
        return Result.success();
    }

     /**
      * 根据ID查询员工详情
      */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询员工详情，参数：{}", id);
        // 调用服务层方法查询员工详情
        Emp emp = empService.getInfo(id);
        // 返回员工详情
        return Result.success(emp);
    }
}
