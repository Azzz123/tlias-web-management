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
     // 新增部门
     @PostMapping("/depts")
     // 通过RequestBody注解，将请求体中的JSON数据绑定到Dept对象中
     // 例如：{"name":"开发部"}
     // 然后在方法参数中声明Dept对象，Mybatis会自动将JSON数据填充到Dept对象中
     // Dept{id=null, name='开发部', createTime=null, updateTime=null}
     // 通常在post和put请求中使用RequestBody注解
    public Result add(@RequestBody Dept dept){
         System.out.println("添加部门："+dept);
         deptService.add(dept);
         return Result.success();
     }
}
