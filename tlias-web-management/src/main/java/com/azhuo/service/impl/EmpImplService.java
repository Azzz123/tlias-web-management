package com.azhuo.service.impl;

import com.azhuo.mapper.EmpMapper;
import com.azhuo.pojo.Emp;
import com.azhuo.pojo.EmpQueryParam;
import com.azhuo.pojo.PageResult;
import com.azhuo.service.EmpService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EmpImplService implements EmpService {
    /**
     * 分页查询员工
     */
    @Autowired
    private final EmpMapper empMapper;

    public EmpImplService(EmpMapper empMapper) {
        this.empMapper = empMapper;
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
}
