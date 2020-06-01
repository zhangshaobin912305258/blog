package com.zhang.blog.annotation;

import com.zhang.blog.config.EncryptRequestBodyAdvice;
import com.zhang.blog.config.EncryptResponseBodyAdvice;
import com.zhang.blog.config.SecretKeyConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:43 下午
 * 创建人：zhang
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecretKeyConfig.class, EncryptResponseBodyAdvice.class, EncryptRequestBodyAdvice.class})
public @interface EnableSecurity {
}
