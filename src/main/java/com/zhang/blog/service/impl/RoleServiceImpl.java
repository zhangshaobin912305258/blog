package com.zhang.blog.service.impl;

import com.zhang.blog.entity.Role;
import com.zhang.blog.mapper.RoleMapper;
import com.zhang.blog.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Override
    public List<Role> getByUserId(long userId) {
        return baseMapper.getByUserId(userId);
    }
}
