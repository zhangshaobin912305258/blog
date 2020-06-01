package com.zhang.blog.annotation;

import java.lang.annotation.*;

/**
 * 类描述：
 * 创建时间：2020/6/1 8:42 下午
 * 创建人：zhang
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decrypt {
}
