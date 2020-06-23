package com.zhang.blog.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
public class LoginDto implements Serializable {
    /**
     * 登录用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String username;
    /**
     * 登录密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;
}
