package com.azhuo.controller;

import com.azhuo.pojo.Dept;
import com.azhuo.pojo.Result;
import com.azhuo.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
// 公共路径抽取用@RequestMapping("/depts")
@RequestMapping("/depts")
public class DeptController {
    // 记录日志写法固定，可以用lombok的@Slf4j注解在类上简化
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    // autowired 构造方法注入部门服务
    @Autowired
    private final DeptService deptService;
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
    @GetMapping
    public Result list() {
        log.info("查询全部部门数据");
//        System.out.println("查询全部部门数据");
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
     @DeleteMapping
     public Result delete(Integer id) {
         log.info("删除部门id为：{}", id);
//         System.out.println("删除部门id为：" + id);
         deptService.delete(id);
         return Result.success();
     }
     // 新增部门
     @PostMapping
     // 通过RequestBody注解，将请求体中的JSON数据绑定到Dept对象中
     // 例如：{"name":"开发部"}
     // 然后在方法参数中声明Dept对象，Mybatis会自动将JSON数据填充到Dept对象中
     // 但是JSON数据中的属性名必须和Dept类中的属性名一致
     // 例如：{"name":"开发部"}
     // Dept{id=null, name='开发部', createTime=null, updateTime=null}
     // 通常在post和put请求中使用RequestBody注解
    public Result add(@RequestBody Dept dept){
         log.info("添加部门：{}", dept);
//         System.out.println("添加部门："+dept);
         deptService.add(dept);
         return Result.success();
     }

     // 查询部门详情
    @GetMapping("/{id}")
     // 路径变量是URL中的一部分，用于传递参数
     // 通过PathVariable注解，将URL中的路径变量绑定到方法参数中。如：/depts/1001
     // 在方法参数中声明Integer id，方法参数和路径变量同名的话，Mybatis会自动将1001填充到id参数中
     // 和@RequestParam不同，路径变量是URL的一部分，必须在URL中指定
     // @RequestParam是在URL中使用?key=value的方式传递参数
     // 而路径变量是在URL中使用/value的方式传递参数
     // 路径变量更符合RESTful风格，也更符合HTTP协议的设计，但只能传递简单类型的参数
    public Result getInfo(@PathVariable("id") Integer id) {
         log.info("查询部门id为：{}", id);
//         System.out.println("查询部门id为：" + id);
         Dept dept = deptService.getById(id);
         return Result.success(dept);
     }

     // 更新部门
    @PutMapping
    public Result update(@RequestBody Dept dept) {
         log.info("更新部门：{}", dept);
//        System.out.println("更新部门：" + dept);
        deptService.update(dept);
        return Result.success();
    }
}
