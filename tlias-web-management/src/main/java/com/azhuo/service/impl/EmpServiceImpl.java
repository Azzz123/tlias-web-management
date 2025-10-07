package com.azhuo.service.impl;

import com.azhuo.mapper.EmpExprMapper;
import com.azhuo.mapper.EmpMapper;
import com.azhuo.pojo.*;
import com.azhuo.service.EmpLogService;
import com.azhuo.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;

import java.util.List;


@Service
public class EmpServiceImpl implements EmpService {
    /**
     * 分页查询员工
     */
    @Autowired
    private final EmpMapper empMapper;

    @Autowired
    private final EmpExprMapper empExprMapper;

    @Autowired
    private final EmpLogService empLogService;

    public EmpServiceImpl(EmpMapper empMapper,
                          EmpExprMapper empExprMapper,
                          EmpLogService empLogService) {
        this.empMapper = empMapper;
        this.empExprMapper = empExprMapper;
        this.empLogService = empLogService;
    }
    /*@Override
    public PageResult<Emp> page(Integer page, Integer pageSize) {
        // 1.计算总数
        Long total = empMapper.count();
        // 2.计算起始索引
        Integer start = (page - 1) * pageSize;
        // 3.查询每页数据
        List<Emp> rows = empMapper.list(start, pageSize);
        // 4.封装分页结果
        return new PageResult<>(total, rows);
    }*/
    /*@Override
    // 分页插件PageHelper实现分页查询方法
    public PageResult<Emp> page(Integer page,
                                Integer pageSize,
                                String name,
                                Integer gender,
                                LocalDate begin,
                                LocalDate end) {
        // 1.设置分页参数
        PageHelper.startPage(page, pageSize);
        // 2.执行查询
        List<Emp> rows = empMapper.list(name, gender, begin, end);
        // 3.解析并封装分页结果
        Page<Emp> p = (Page<Emp>) rows;
        return new PageResult<>(p.getTotal(), p.getResult());
    }*/

    @Override
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {
        // 1. 将 startPage 调用直接放入 try-with-resources 语句
        //    这样就捕获了它返回的 AutoCloseable 对象 page，并确保它最后会被 close()
        try (Page<Object> page = PageHelper.startPage(
                empQueryParam.getPage(),
                empQueryParam.getPageSize()
        )) {
            // 2. 在 try 块内正常执行查询
            List<Emp> rows = empMapper.list(empQueryParam);
            // 3. 使用 page 对象获取总数，使用查询结果 rows 获取数据列表
            //    PageHelper 拦截器在执行完查询后，会自动把总记录数回填到 try() 中创建的 page 对象里
            return new PageResult<>(page.getTotal(), rows);
        }
    }

    /**
     * 新增员工
     */

    @Override
    @Transactional
    public void save(Emp emp) {
        try {
            // 1. 调用 Mapper 层方法保存员工
            // 1.1 补全基础属性
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            // 1.2. 调用 Mapper 层方法保存员工
            // 这里使用了 mybatis的主键返回 useGeneratedKeys="true" keyProperty="id" 来获取自动生成的主键值
            // 并将其设置到 emp 对象的 id 属性中
            empMapper.insert(emp);

//          测试事务回滚，模拟异常情况
//            int i = 1 / 0;

            // 2. 保存工作经历
            List<EmpExpr> exprList = emp.getExprList();
            if (!CollectionUtils.isEmpty(exprList)) {
                // 2.1 补全工作经历基础属性，设置员工ID为返回的主键值
                exprList.forEach(expr -> expr.setEmpId(emp.getId()));
                // 2.2 调用 Mapper 层方法保存工作经历
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            // 3.记录操作日志（不管成功/失败）
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), "新增员工信息："+emp);
            empLogService.insertLog(empLog);
        }

    }

     /**
      * 批量删除员工
      */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(List<Integer> ids) {
        // 1.删除员工基本信息
        empMapper.deleteByIds(ids);
        // 2.删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
        // 3.删除阿里云OSS

    }
}
