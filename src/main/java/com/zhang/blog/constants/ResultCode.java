package com.zhang.blog.constants;

import lombok.Data;

@Data
public class ResultCode {
    public static final ResultCode SUCCESS = new ResultCode(0, "操作成功");
    public static final ResultCode USER_NOT_FOUND = new ResultCode(0, "用户名或密码错误");
    public static final ResultCode PARAM_ERROR = new ResultCode(400, "参数错误");
    public static final ResultCode DECRYPTION_FAILED = new ResultCode(400, "解密失败,请检查参数");
    public static final ResultCode UNAUTHORIZED = new ResultCode(403, "权限校验失败");
    public static final ResultCode ERROR = new ResultCode(-1, "全局错误");

    private int code;
    private String msg;

    public ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultCode(ResultCode resultCode, String msg) {
        this.code = resultCode.getCode();
        this.msg = msg;
    }
}
