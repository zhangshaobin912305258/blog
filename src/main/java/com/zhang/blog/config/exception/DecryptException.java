package com.zhang.blog.config.exception;

/**
 * 类描述：解密异常
 * 创建时间：2020/6/23 5:25 下午
 * 创建人：zhang
 */
public class DecryptException extends RuntimeException{

    public DecryptException(String message) {
        super(message);
    }
}
