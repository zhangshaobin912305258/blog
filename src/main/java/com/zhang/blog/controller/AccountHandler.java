package com.zhang.blog.controller;

import com.zhang.blog.service.UserService;
import com.zhang.blog.util.JwtUtils;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.LoginDto;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountHandler extends BaseController{
    private final UserService userService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto, httpServletResponse);
    }

    @RequiresAuthentication
    @PostMapping("/logout")
    public Result logout(@Validated @RequestBody LoginDto loginDto) {
        SecurityUtils.getSubject().logout();
        return Result.ok();
    }
}
