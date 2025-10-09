package com.azhuo.service;

import com.azhuo.pojo.Clazz;
import com.azhuo.pojo.ClazzQueryParam;
import com.azhuo.pojo.PageResult;

import java.util.List;

public interface ClazzService {
    /**
     * 分页查询班级
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);
    /**
     * 添加班级
     */
    void save(Clazz clazz);
    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void update(Clazz clazz);

     /**
      * 根据ID删除班级
      */
    void deleteById(Integer id);

    /**
     * 查询所有班级
     */
    List<Clazz> getAll();
}
