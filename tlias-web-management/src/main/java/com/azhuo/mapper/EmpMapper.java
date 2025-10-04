package com.azhuo.mapper;

import com.azhuo.pojo.Emp;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// 员工基本信息操作接口
@Mapper
public interface EmpMapper {
    /**
     * 查询员工总数
     * @return 员工总数
     */
    @Select("SELECT COUNT(*) FROM emp")
    Long count();

    /**
     * 分页查询
     * @return 分页结果
     */
    @Select("SELECT e.*, d.name AS deptName FROM emp e " +
            "LEFT JOIN dept d " +
            "ON d.id = e.dept_id " +
            "ORDER BY e.update_time DESC " +
            "LIMIT #{start}, #{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);
}
