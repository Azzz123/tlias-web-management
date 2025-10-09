package com.azhuo.mapper;

import com.azhuo.pojo.Student;
import com.azhuo.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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
    /**
     * 根据ID查询学生
     */
    Student selectById(Integer id);
    /**
     * 批量删除学生
     */
    void delete(List<Integer> ids);
    /**
     * 修改学生
     */
    void update(Student student);


    /**
     * 统计班级人数
     */
    List<Map<String, Object>> countStudentCount();

    /**
     * 统计学员学历
     */
    List<Map<String, Object>> countStudentDegreeData();
}
