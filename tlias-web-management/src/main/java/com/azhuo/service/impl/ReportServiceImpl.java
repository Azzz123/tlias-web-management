package com.azhuo.service.impl;

import com.azhuo.mapper.ClazzMapper;
import com.azhuo.mapper.EmpMapper;
import com.azhuo.pojo.JobOption;
import com.azhuo.pojo.StudentClazzData;
import com.azhuo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private final EmpMapper empMapper;
    @Autowired
    private final ClazzMapper clazzMapper;
    public ReportServiceImpl(EmpMapper empMapper, ClazzMapper clazzMapper) {
        this.empMapper = empMapper;
        this.clazzMapper = clazzMapper;
    }

    /**
     * 统计员工职位数量
     */
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();

        List<Object> jobList = list.stream().map(map -> map.get("pos")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("num")).toList();

        return new JobOption(jobList, dataList);
    }

     /**
      * 统计员工性别数量
      */
    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
     /**
      * 统计班级人数
      */
    @Override
    public StudentClazzData getStudentCount() {
        List<Map<String, Object>> list = clazzMapper.countStudentCount();

        List<Object> clazzList = list.stream().map(map -> map.get("clazz_name")).toList();
        List<Object> dataList = list.stream().map(map -> map.get("num")).toList();

        return new StudentClazzData(clazzList, dataList);
    }
}
