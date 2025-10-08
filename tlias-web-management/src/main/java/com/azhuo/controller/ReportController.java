package com.azhuo.controller;

import com.azhuo.pojo.JobOption;
import com.azhuo.pojo.Result;
import com.azhuo.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    private final ReportService reportService;
    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * 统计员工职位数量
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData() {
        log.info("统计员工职位数量");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }

    /**
     * 统计员工性别数量
     */
    @GetMapping("/empGenderData")
    public Result getEmpGenderData() {
        log.info("统计员工性别数量");
        List<Map<String, Object>> list = reportService.getEmpGenderData();
        return Result.success(list);
    }
}
