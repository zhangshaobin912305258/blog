package com.zhang.blog.service.impl;

import com.zhang.blog.entity.Permission;
import com.zhang.blog.mapper.PermissionMapper;
import com.zhang.blog.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> getByUserId(long userId) {
        return baseMapper.getByUserId(userId);
    }
}
