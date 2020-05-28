package com.zhang.blog.controller;


import com.zhang.blog.constants.ResultCode;
import com.zhang.blog.entity.User;
import com.zhang.blog.service.UserService;
import com.zhang.blog.vo.Result;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author zhang
 * @since 2020-05-28
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @RequiresAuthentication
    @GetMapping("/")
    public Result<List<User>> getUsers() {
        return Result.build(ResultCode.SUCCESS, userService.list());
    }

    @PostMapping("/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.ok(user);
    }
}
