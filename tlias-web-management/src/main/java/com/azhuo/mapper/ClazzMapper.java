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
}
