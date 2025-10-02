package com.azhuo.mapper;

import com.azhuo.pojo.Dept;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 查询全部部门数据
    @Select("SELECT id, name, create_time, update_time FROM dept ORDER BY update_time DESC")
    List<Dept> findAll();

    @Delete("DELETE FROM dept WHERE id = #{id}")
    void delete(Integer id);
}
