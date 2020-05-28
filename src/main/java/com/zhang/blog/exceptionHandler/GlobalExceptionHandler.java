package com.zhang.blog.exceptionHandler;

import com.zhang.blog.constants.ResultCode;
import com.zhang.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e) {
        log.error("运行时异常：----------------{}", e);
        return Result.fail(ResultCode.UNAUTHORIZED, e.getMessage());
    }

    //@ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = ShiroException.class)
    public Result handler(ShiroException e) {
        //log.error("权限校验异常：----------------{}", e);
        return Result.fail(ResultCode.UNAUTHORIZED, e.getMessage());
    }


    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        log.info("参数校验异常：----------------{}", objectError.getDefaultMessage());
        return Result.fail(ResultCode.PARAM_ERROR, objectError.getDefaultMessage());
    }

    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e) {
        log.info("Assert异常：----------------{}", e.getMessage());
        return Result.fail(ResultCode.PARAM_ERROR, e.getMessage());
    }

}
