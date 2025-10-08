package com.azhuo.service;

import com.azhuo.pojo.JobOption;

public interface ReportService {
    /**
     * 统计员工职位数量
     */
    JobOption getEmpJobData();

}
