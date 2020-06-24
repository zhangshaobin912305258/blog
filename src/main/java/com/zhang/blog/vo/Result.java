package com.zhang.blog.vo;

import com.zhang.blog.constants.ResultCode;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class Result<T> implements Serializable {
    //响应状态码 0:正常  非0:异常
    private int code;
    //响应消息
    private String msg;
    //响应内容
    private T data;

    public static <T> Result ok() {
        return build(ResultCode.SUCCESS, null);
    }

    public static <T> Result ok(T data) {
        return build(ResultCode.SUCCESS, data);
    }

    public static Result fail(ResultCode resultCode) {
        return build(resultCode.getCode(), resultCode.getMsg(), null);
    }

    public static <T> Result fail(ResultCode resultCode, String msg) {
        return build(resultCode.getCode(), msg, null);
    }

    public static <T> Result fail(ResultCode resultCode, T data) {
        return build(resultCode.getCode(), resultCode.getMsg(), data);
    }

    public static <T> Result fail(ResultCode resultCode, String msg, T data) {
        return build(resultCode.getCode(), msg, data);
    }

    public static <T> Result build(ResultCode resultCode, T data) {
        return build(resultCode.getCode(), resultCode.getMsg(), data);
    }


    public static <T> Result build(int code, String msg, T data) {
        return Result.builder()
                .code(code)
                .msg(msg)
                .data(data)
                .build();
    }


}
