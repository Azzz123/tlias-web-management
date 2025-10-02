package com.azhuo.controller;

import com.azhuo.pojo.Dept;
import com.azhuo.pojo.Result;
import com.azhuo.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    // autowired 构造方法注入部门服务
    @Autowired
    private final DeptService deptService;
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    @GetMapping("/depts")
    public Result list() {
        System.out.println("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }
     // 删除部门
//    三种方法实现参数传递
//    1.HttpServletRequest
/*    @DeleteMapping("/depts")
    public Result del(HttpServletRequest request) {
        // 获取并转换参数
        int id = Integer.parseInt(request.getParameter("id"));
        // 假装删除
        System.out.println("删除部门id为：" + id);
        return Result.success();
    }*/
//    2.@RequestParam
/*     @DeleteMapping("/depts")
     public Result del(@RequestParam(value = "id", required = true) Integer id) {
        // 假装删除
        System.out.println("删除部门id为：" + id);
        return Result.success();
     }*/
//    3.如果参数是简单类型，如int、Integer等，且请求名和方法形参一致直接在方法参数中声明即可。
     @DeleteMapping("/depts")
     public Result delete(Integer id) {
         deptService.delete(id);
         return Result.success();
     }
}
