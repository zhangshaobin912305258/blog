package com.zhang.blog.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Aspect
@Slf4j
@Component
public class AopConfig {
    @Pointcut("execution(* com.zhang.blog.controller..*(..)))")
    private void logPointCut() {
    }

    @Around("logPointCut()")
    public Object logPrint(ProceedingJoinPoint point) throws Throwable {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String requestTime = DateUtil.formatLocalDateTime(LocalDateTime.now());
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        Object[] args = point.getArgs();
        String params = "";
        //获取请求参数集合并进行遍历拼接
        if (args.length > 0) {
            if ("POST".equals(method)) {
                Object[] arguments = new Object[args.length];
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof ServletRequest || args[i] instanceof ServletResponse || args[i] instanceof MultipartFile) {
                        continue;
                    }
                    arguments[i] = args[i];
                }
                if (arguments != null) {
                    try {
                        params = JSONUtil.toJsonStr(arguments);
                    } catch (Exception e) {
                        params = arguments.toString();
                    }
                }
            } else if ("GET".equals(method)) {
                params = queryString;
            }
        }
        if (StringUtils.isNotEmpty(queryString)) {
            uri = uri + "?" + queryString;
        }
        log.info("请求时间:{},请求方法:{},请求地址:{},请求参数:\n{},", requestTime, method, uri, params);
        long startTime = System.currentTimeMillis();
        // result的值就是被拦截方法的返回值
        Object result = null;
        try {
            result = point.proceed();
        } catch (Exception e) {
            log.info(e.getMessage());
        }
        long endTime = System.currentTimeMillis();
        log.info("请求时间:{},请求方法:{},请求地址:{},请求参数:\n{},响应:{},请求耗时:{}ms.", requestTime, method, uri, params,
                JSONUtil.toJsonStr(result), (endTime - startTime));
        return result;
    }

}
