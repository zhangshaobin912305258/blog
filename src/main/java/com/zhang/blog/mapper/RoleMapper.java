package com.zhang.blog.mapper;

import com.zhang.blog.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface RoleMapper extends BaseMapper<Role> {
    List<Role> getByUserId(long userId);

}
