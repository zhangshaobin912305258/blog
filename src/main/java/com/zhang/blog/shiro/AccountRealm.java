package com.zhang.blog.shiro;

import cn.hutool.core.bean.BeanUtil;
import com.zhang.blog.entity.User;
import com.zhang.blog.service.UserService;
import com.zhang.blog.util.JwtUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountRealm extends AuthorizingRealm {

    private final JwtUtils jwtUtils;
    private final UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    /**
     * 身份校验
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        JwtToken jwtToken = (JwtToken) authenticationToken;
        Claims claim = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal());
        String userId = claim.getSubject();
        User user = userService.getById(Long.parseLong(userId));
        if (user == null) {
            throw new UnknownAccountException("账户不存在");
        }
        if (user.getStatus() == -1) {
            throw new LockedAccountException("账户已被锁定");
        }
        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(user, profile);
        return new SimpleAuthenticationInfo(profile, jwtToken.getCredentials(), getName());
    }
}
