package com.zhang.blog.controller;

import com.zhang.blog.annotation.Decrypt;
import com.zhang.blog.annotation.Encrypt;
import com.zhang.blog.entity.User;
import com.zhang.blog.vo.Result;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 类描述：
 * 创建时间：2020/6/1 4:13 下午
 * 创建人：zhang
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    @PreAuthorize(value = "hasAuthority('user:add')")
    public Result test() {
        return Result.ok();
    }

    /**
     * 对返回值加密
     * @return
     */
    @Encrypt
    @GetMapping("/test01")
    public Result test01(){
        User testBean = new User();
        testBean.setUsername("张");
        testBean.setId(18L);
        return Result.ok(testBean);
    }

    /**
     * 对传过来的加密参数进行解密
     * @param testBean
     * @return
     */
    @Decrypt
    @PostMapping("/test02")
    @Encrypt
    public Result test02(@RequestBody User testBean){
        return Result.ok(testBean);
    }

}
