package com.azhuo.service;

import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;

import java.util.List;

public interface StudentService {
    /**
     * 分页查询学生
     */
   PageResult<Student> page(StudentQueryParam studentQueryParam);
    /**
     * 添加学员
     */
    void add(Student student);
    /**
     * 根据ID查询学生
     */
    Student getById(Integer id);
    /**
     * 批量删除学生
     */
    void delete(List<Integer> ids);
    /**
     * 修改学生
     */
    void update(Student student);
    /**
     * 违纪处理
     */
    void violation(Integer id, Short score);
}
