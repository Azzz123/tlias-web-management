package com.azhuo.mapper;

import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {
    /**
     * 分页查询班级
     */
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 新增班级
     */
    void insert(Clazz clazz);
    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);
    /**
     * 修改班级
     */
    void update(Clazz clazz);

     /**
      * 根据班级ID查询班级下的学生数量
      */
    int countStudentByClazzId(Integer id);

     /**
      * 根据ID删除班级
      */
    void deleteById(Integer id);
}
