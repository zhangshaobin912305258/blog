package com.zhang.blog.constants;

public enum ResultCode {
    SUCCESS(0, "操作成功"),
    PARAM_ERROR(400, "参数错误"),
    OPERATION_ERROR(1001, "操作错误"),
    UNAUTHORIZED(401, "未授权"),
    ;


    private int code;
    private String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
