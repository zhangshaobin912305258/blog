package com.zhang.blog.config;

import cn.hutool.core.codec.Base64;
import cn.hutool.json.JSONUtil;
import com.zhang.blog.annotation.Encrypt;
import com.zhang.blog.util.RSAUtil;
import com.zhang.blog.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:38 下午
 * 创建人：zhang
 */
@ControllerAdvice
@Slf4j
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Result> {

    private boolean encrypt;
    @Autowired
    private SecretKeyConfig secretKeyConfig;

    private static ThreadLocal<Boolean> encryptLocal = new ThreadLocal();

    public EncryptResponseBodyAdvice() {
    }

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        this.encrypt = false;
        if (returnType.getMethod().isAnnotationPresent(Encrypt.class) && !this.secretKeyConfig.isDebug()) {
            this.encrypt = true;
        }

        return this.encrypt;
    }

    @Override
    public Result beforeBodyWrite(Result body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Boolean status = (Boolean) encryptLocal.get();
        if (null != status && !status) {
            encryptLocal.remove();
            return body;
        } else {
            if (this.encrypt) {
                String publicKey = this.secretKeyConfig.getPublicKey();
                try {
                    Object data = body.getData();
                    String content = JSONUtil.toJsonStr(data);
                    if (!StringUtils.hasText(publicKey)) {
                        throw new NullPointerException("请配置RSA公钥!");
                    }

                    byte[] bytes = content.getBytes();
                    byte[] encodedData = RSAUtil.encrypt(bytes, publicKey);
                    String result = Base64.encode(encodedData);
                    log.info("加密前报文:{}", content);
                    log.info("加密后报文:{}", result);
                    body.setData(result);
                    return body;
                } catch (Exception var13) {
                    log.error("加密失败:", var13);
                }
            }
            return body;
        }
    }
}
