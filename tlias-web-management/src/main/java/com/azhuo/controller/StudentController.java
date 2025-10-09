package com.azhuo.controller;

import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Result;
import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;
import com.azhuo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private final StudentService studentService;
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 分页查询学生
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("分页查询学生: {}", studentQueryParam.toString());
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加学员
     */
    @PostMapping
    public Result add(@RequestBody Student student) {
        log.info("添加学员: {}", student.toString());
        studentService.add(student);
        return Result.success(null);
    }
}
