package com.zhang.blog.mapper;

import com.zhang.blog.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> getByUserId(long userId);
}
