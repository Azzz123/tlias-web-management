package com.azhuo.mapper;

import com.azhuo.pojo.Emp;
import com.azhuo.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

// 员工基本信息操作接口
@Mapper
public interface EmpMapper {
    // 原始分页查询方法实现

    /*@Select("SELECT COUNT(*) FROM emp")
    Long count();*/


    /*@Select("SELECT e.*, d.name AS deptName FROM emp e " +
            "LEFT JOIN dept d " +
            "ON d.id = e.dept_id " +
            "ORDER BY e.update_time DESC " +
            "LIMIT #{start}, #{pageSize}")
    List<Emp> list(Integer start, Integer pageSize);*/

    // 分页插件分页查询方法实现PageHelper, 不需要自己编写分页查询方法，参数也不需要添加

//    @Select("SELECT e.*, d.name AS deptName FROM emp e " +
//            "LEFT JOIN dept d " +
//            "ON d.id = e.dept_id " +
//            "ORDER BY e.update_time DESC ")
/*    List<Emp> list(String name,
                   Integer gender,
                   LocalDate begin,
                   LocalDate end);*/
    /*
     * 条件分页查询
     * @return 分页结果
     */

//    输入 员工名称 进行搜索，支持模糊查询。
//    选择 员工性别 进行精确查询。
//    选择 入职时间的开始时间 和 结束时间，可以进行范围查询对查询结果
//    根据修改时间倒序排序，并对查询结果进行分页展示
    /**
     * 分页查询
     * @return 分页结果
     */
    List<Emp> list(EmpQueryParam empQueryParam);

    /**
     * 新增员工
     */
    void insert(Emp emp);

    /**
     * 批量删除员工
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 根据ID查询员工
     */
    Emp getById(Integer id);

    /**
     * 更新员工信息
     */
    void updateById(Emp emp);

    /**
     * 统计员工职位人数
     */
    List<Map<String, Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     */
    List<Map<String, Object>> countEmpGenderData();

     /**
      * 查询全部员工
      */
    List<Emp> getAll();
    /**
     * 根据部门ID查询员工数量
     */
    int countByDeptId(Integer id);
     /**
      * 根据用户名和密码查询员工
      */
    Emp selectByUsernameAndPassword(String username, String password);
}
