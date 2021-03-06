package com.zhang.blog.security;

import com.zhang.blog.annotation.Decrypt;
import com.zhang.blog.config.SecretKeyConfig;
import com.zhang.blog.config.exception.BaseException;
import com.zhang.blog.constants.Const;
import com.zhang.blog.constants.ResultCode;
import com.zhang.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.lang.reflect.Type;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:36 下午
 * 创建人：zhang
 */
@ControllerAdvice
@Slf4j
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {

    private boolean encrypt;

    @Autowired
    private SecretKeyConfig secretKeyConfig;

    public EncryptRequestBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (methodParameter.getMethod().isAnnotationPresent(Decrypt.class) && !this.secretKeyConfig.isDebug()) {
            this.encrypt = true;
        }

        return this.encrypt;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (this.encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage, this.secretKeyConfig.getPrivateKey(), this.secretKeyConfig.getCharset());
            } catch (Exception e) {
                log.error("解密失败:{}", e.getMessage());
                throw new BaseException(Result.fail(ResultCode.DECRYPTION_FAILED));
            }
        }
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
