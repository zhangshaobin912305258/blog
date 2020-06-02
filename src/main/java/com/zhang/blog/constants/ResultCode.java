package com.zhang.blog.constants;

import lombok.Data;

@Data
public class ResultCode {
    public static final ResultCode SUCCESS = new ResultCode(0, "操作成功");
    public static final ResultCode PARAM_ERROR = new ResultCode(400, "操作成功");
    public static final ResultCode UNAUTHORIZED = new ResultCode(403, "操作成功");

    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
