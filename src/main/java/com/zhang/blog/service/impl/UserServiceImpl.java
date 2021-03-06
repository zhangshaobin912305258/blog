package com.zhang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.blog.constants.Const;
import com.zhang.blog.constants.ResultCode;
import com.zhang.blog.entity.Permission;
import com.zhang.blog.entity.Role;
import com.zhang.blog.entity.User;
import com.zhang.blog.mapper.UserMapper;
import com.zhang.blog.security.GrantedAuthorityImpl;
import com.zhang.blog.service.PermissionService;
import com.zhang.blog.service.RoleService;
import com.zhang.blog.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.blog.util.AssertUtil;
import com.zhang.blog.util.JwtTokenUtil;
import com.zhang.blog.util.ListUtils;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.converter.UserConverter;
import com.zhang.blog.vo.request.LoginDto;
import com.zhang.blog.vo.response.UserVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-28
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService, UserDetailsService {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserConverter userConverter;


/*    @Override
    public Result login(LoginDto loginDto, HttpServletResponse httpServletResponse) {
        String username = loginDto.getUsername();
        User user = getByUsername(username);
        Assert.notNull(username, "用户名或密码错误");
        String password = loginDto.getPassword();
        String salt = user.getSalt();
        String md5Password = SecureUtil.md5(password+salt);
        Assert.isTrue(!md5Password.equals(user.getPassword()), "用户名或密码错误");
        String jwt = jwtUtils.generateToken(user.getId());
        httpServletResponse.setHeader("Authorization", jwt);
        httpServletResponse.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.ok(UserConverter.INSTANCE.domain2Vo(user));
    }*/

    @Override
    public User getByUsername(String username) {
        AssertUtil.notEmpty(username, Result.fail(ResultCode.PARAM_ERROR, Const.USER_NOT_EMPTY));
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username);
        return getOne(queryWrapper);
    }

    @Override
    public Result<UserVo> login(LoginDto loginDto, HttpServletResponse httpServletResponse) {
        String username = loginDto.getUsername();
        String password = loginDto.getPassword();
        User user = getByUsername(username);
        AssertUtil.notNull(user, Result.fail(ResultCode.USER_NOT_FOUND));
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        loadUserByUsername(username);
        String token = jwtTokenUtil.generateToken(user);
        httpServletResponse.setHeader("Authorization", token);
        httpServletResponse.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.ok(userConverter.toDto(user));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 查数据库
        User user = getByUsername(userName);
        AssertUtil.notNull(user, Result.fail(ResultCode.USER_NOT_FOUND));
        Long userId = user.getId();
        List<Role> roles = roleService.getByUserId(userId);
        if (ListUtils.isNotEmpty(roles)) {
            user.setRoles(roles.stream()
                    .map(Role::getRoleName)
                    .collect(Collectors.toList()));
        }
        List<Permission> permissions = permissionService.getByUserId(userId);
        if (ListUtils.isNotEmpty(permissions)) {
            user.setAuthorities(permissions.stream()
                    .map(Permission::getPermissionName)
                    .distinct()
                    .filter(permission -> StringUtils.isNotEmpty(permission))
                    .map(GrantedAuthorityImpl::new)
                    .collect(Collectors.toList()));
        }
        return user;
    }
}
