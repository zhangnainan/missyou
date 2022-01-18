package com.lin.missyou.core;

import com.lin.missyou.core.configuration.ExceptionCodeConfiguration;
import com.lin.missyou.exception.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionAdvice {

    @Autowired
    private ExceptionCodeConfiguration codeConfiguration;

    @ExceptionHandler(value = {Exception.class,RuntimeException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest request, Exception e){
        //System.out.println("exception");
        // 如果UnifyResponse几个成员变量不设置getter，SpringBoot将无法对该对象进行序列化，导致报错。
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        UnifyResponse message = new UnifyResponse(9999,"服务器错误",method+" "+requestUrl);

        return message;
    }

    @ExceptionHandler(HttpException.class)
    public ResponseEntity<UnifyResponse> handleHttpException(HttpServletRequest request, HttpException e){
        String requestUrl = request.getRequestURI();
        String method = request.getMethod();
        UnifyResponse message = new UnifyResponse(e.getCode(),codeConfiguration.getMessage(e.getCode()),method+" "+requestUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpStatus httpStatus = HttpStatus.resolve(e.getHttpStatusCode());
        ResponseEntity<UnifyResponse> r = new ResponseEntity<UnifyResponse>(message, headers, httpStatus);

        return r;
    }
}
