package com.zhang.blog.service;

import com.zhang.blog.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface RoleService extends IService<Role> {

    List<Role> getByUserId(long userId);
}
