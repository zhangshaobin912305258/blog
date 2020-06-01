package com.zhang.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:44 下午
 * 创建人：zhang
 */
@ConfigurationProperties(prefix = "rsa.encrypt")
@Configuration
@Data
public class SecretKeyConfig {
    private String privateKey;
    private String publicKey;
    private String charset = "UTF-8";
    private boolean debug = false;
}
