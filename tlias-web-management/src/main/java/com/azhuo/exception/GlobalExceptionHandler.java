package com.azhuo.exception;

import com.azhuo.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("发生异常：{}", e.getMessage(), e);
        return Result.error("操作失败");
    }

    /**
     * 处理重复键异常
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.error("程序出错啦~", e);
        String message = e.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split("");
        return Result.error(arr[2]+"已存在");
    }

    /**
     * 处理数据关联异常
     */
    @ExceptionHandler(DataRelationViolationException.class)
    public Result handleDataRelationViolationException(DataRelationViolationException e) {
        log.error("程序数据关联出错啦~", e);
        return Result.error(e.getMessage());
    }
}
