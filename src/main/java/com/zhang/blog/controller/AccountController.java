package com.zhang.blog.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.map.MapUtil;
import com.zhang.blog.annotation.Decrypt;
import com.zhang.blog.service.UserService;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.LoginDto;
import com.zhang.blog.vo.response.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * 账号api
 */
@RestController
@RequiredArgsConstructor
public class AccountController extends BaseController {
    private final UserService userService;

    /**
     * 生成图片验证码
     * @return
     * @throws IOException
     */
    @GetMapping("captcha.jpg")
    public Result<Map> captcha()  {
        LineCaptcha captcha = CaptchaUtil.createLineCaptcha(200, 100, 5, 50);
        String img = captcha.getImageBase64();
        String code = captcha.getCode();
        return Result.ok(MapUtil.builder()
                .put("image", "data:image/png;base64," + img)
                .put("code", code)
                .build());
    }

    /**
     * 用户登录请求
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    @Decrypt
    public Result<UserVo> login(@Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto, httpServletResponse);
    }

    /*@RequiresAuthentication
    @PostMapping("/logout")
    public Result logout(@Validated @RequestBody LoginDto loginDto) {
        SecurityUtils.getSubject().logout();
        return Result.ok();
    }*/
}
