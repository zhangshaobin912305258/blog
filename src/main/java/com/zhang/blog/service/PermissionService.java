package com.zhang.blog.service;

import com.zhang.blog.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface PermissionService extends IService<Permission> {

    List<Permission> getByUserId(long userId);
}
