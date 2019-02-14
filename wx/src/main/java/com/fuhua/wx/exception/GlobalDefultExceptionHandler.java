package com.fuhua.wx.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常 简单版，复制需要返回rest数据
 * https://blog.csdn.net/qq_34083066/article/details/79424142
 * https://blog.csdn.net/u014044812/article/details/78219692
 */
@ControllerAdvice
@Slf4j
public class GlobalDefultExceptionHandler {
    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String defultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if (e instanceof WxException) {
            log.error("业务异常：" + e.getMessage());
            WxException wxException = (WxException) e;
            return wxException.getMessage();
        }
        //未知错误
        return "系统异常";
    }
}


