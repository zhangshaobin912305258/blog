package com.zhang.blog.service;

import com.zhang.blog.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.LoginDto;
import com.zhang.blog.vo.response.UserVo;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-28
 */
public interface UserService extends IService<User> {

    /*Result login(LoginDto loginDto, HttpServletResponse httpServletResponse);*/

    User getByUsername(String userName);

    Result<UserVo> login(LoginDto loginDto, HttpServletResponse httpServletResponse);
}
