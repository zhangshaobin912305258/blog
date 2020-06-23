package com.zhang.blog.vo.mapper;

import com.zhang.blog.entity.User;
import com.zhang.blog.vo.response.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 类描述：
 * 创建时间：2020/6/23 5:12 下午
 * 创建人：zhang
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<UserVo, User>{
}
