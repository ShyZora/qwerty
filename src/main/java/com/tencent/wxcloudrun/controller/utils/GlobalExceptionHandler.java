package com.tencent.wxcloudrun.controller.utils;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public R handleException(Exception ex)
    {
        ex.printStackTrace();
        return new R(false, "服务器故障，请稍后再试");
    }

}
