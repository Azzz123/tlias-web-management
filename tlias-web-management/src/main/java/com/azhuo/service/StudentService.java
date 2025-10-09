package com.azhuo.service;

import com.azhuo.pojo.PageResult;
import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;

public interface StudentService {
    /**
     * 分页查询学生
     */
   PageResult<Student> page(StudentQueryParam studentQueryParam);
}
