package com.azhuo.service.impl;

import com.azhuo.mapper.EmpMapper;
import com.azhuo.pojo.JobOption;
import com.azhuo.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private final EmpMapper empMapper;
    public ReportServiceImpl(EmpMapper empMapper) {
        this.empMapper = empMapper;
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

    @Override
    public List<Map<String, Object>> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }
}
