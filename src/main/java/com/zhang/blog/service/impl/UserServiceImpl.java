package com.zhang.blog.service.impl;

import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.blog.entity.User;
import com.zhang.blog.mapper.UserMapper;
import com.zhang.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.blog.util.JwtUtils;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.LoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-28
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    private final JwtUtils jwtUtils;

    @Override
    public Result login(LoginDto loginDto, HttpServletResponse httpServletResponse) {
        String username = loginDto.getUsername();
        User user = getByUsername(username);
        Assert.notNull(username, "用户名或密码错误");
        String password = loginDto.getPassword();
        String md5Password = SecureUtil.md5(password);
        Assert.isTrue(!md5Password.equals(user.getPassword()), "用户名或密码错误");
        String jwt = jwtUtils.generateToken(user.getId());
        httpServletResponse.setHeader("Authorization", jwt);
        httpServletResponse.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.ok(MapUtil.builder()
                .put("id", user.getId())
                .put("username", user.getUsername())
                .put("avatar", user.getAvatar())
                .put("email", user.getEmail())
        );
    }

    public User getByUsername(String username) {
        Assert.notNull(username, "用户名不能为空");
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }
}
