package com.azhuo.controller;

import com.azhuo.anno.Log;
import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Result;
import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;
import com.azhuo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @Log
    public Result add(@RequestBody Student student) {
        log.info("添加学员: {}", student.toString());
        studentService.add(student);
        return Result.success(null);
    }

    /**
     * 根据ID查询学生
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学生: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 批量删除学生
     */
    @DeleteMapping("/{ids}")
    @Log
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除学生: {}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    /**
     * 修改学生
     */
    @PutMapping
    @Log
    public Result update(@RequestBody Student student) {
        log.info("修改学生: {}", student.toString());
        studentService.update(student);
        return Result.success();
    }
    /**
     * 违纪处理
     */
    @PutMapping("/violation/{id}/{score}")
    public Result violation(@PathVariable Integer id,
                            @PathVariable Short score) {
        log.info("违纪处理: {}", id);
        studentService.violation(id, score);
        return Result.success();
    }
}
