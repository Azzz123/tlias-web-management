package com.azhuo.mapper;

import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StudentMapper {
    /**
     * 分页查询学生
     */
    List<Student> page(StudentQueryParam studentQueryParam);
    /**
     * 添加学员
     */
    void insert(Student student);
}
