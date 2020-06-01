package com.zhang.blog.service.impl;

import com.zhang.blog.entity.UserRole;
import com.zhang.blog.mapper.UserRoleMapper;
import com.zhang.blog.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色关联 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
