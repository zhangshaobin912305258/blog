package com.zhang.blog.converter;

import com.zhang.blog.entity.User;
import com.zhang.blog.vo.response.UserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 类描述：
 * 创建时间：2020/6/1 10:51 上午
 * 创建人：zhang
 */
@Mapper
public interface UserConverter {

    UserConverter INSTANCE = Mappers.getMapper(UserConverter.class);

    UserVo domain2Vo(User user);

    List<UserVo> domain2Vo(List<User> users);
}
