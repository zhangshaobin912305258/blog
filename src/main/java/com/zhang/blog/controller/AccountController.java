package com.zhang.blog.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.RandomUtil;
import com.zhang.blog.service.UserService;
import com.zhang.blog.util.SecurityUtils;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.LoginDto;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class AccountController extends BaseController{
    private final UserService userService;

    /*@GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, HttpServletRequest request) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(50, 50);
        String text = RandomUtil.randomString(4);
        Image image = lineCaptcha.createImage(text);
         生成文字验证码
        String text = producer.createText();
        // 生成图片验证码
        BufferedImage image = producer.createImage(text);*//*
        // 保存到验证码到 session
        request.getSession().setAttribute("Captcha_key", text);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }*/

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto) {
        return userService.login(loginDto, httpServletResponse);
    }

    /*@RequiresAuthentication
    @PostMapping("/logout")
    public Result logout(@Validated @RequestBody LoginDto loginDto) {
        SecurityUtils.getSubject().logout();
        return Result.ok();
    }*/
}
