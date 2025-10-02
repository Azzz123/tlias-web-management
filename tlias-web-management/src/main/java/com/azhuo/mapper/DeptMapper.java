package com.azhuo.mapper;

import com.azhuo.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    // 查询全部部门数据
    @Select("SELECT id, name, create_time, update_time FROM dept ORDER BY update_time DESC")
    List<Dept> findAll();

    // 删除部门
    @Delete("DELETE FROM dept WHERE id = #{id}")
    void delete(Integer id);

    // 新增部门
    // #{name}, #{createTime}, #{updateTime}
    // 必须和Dept类中的属性名一致
    // Mybatis会根据Dept类中的属性名，自动将属性值填充到SQL语句中的占位符中
    // 还有一种方式，就是使用@Param注解，指定参数名
    // 这样就可以在SQL语句中使用#{paramName}来引用参数值
    // 例如：@Param("name") String name
    // 然后在SQL语句中使用#{name}来引用参数值
    @Insert("INSERT INTO dept(name, create_time, update_time) VALUES (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("SELECT id, name, create_time, update_time FROM dept WHERE id = #{id}")
    Dept getById(Integer id);

     // 更新部门
    @Update("UPDATE dept SET id=#{id}, name=#{name}, update_time=#{updateTime} WHERE id = #{id}")
    void update(Dept dept);
}
