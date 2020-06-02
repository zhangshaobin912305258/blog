package com.zhang.blog.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.blog.vo.response.PageResponseVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * 类描述：
 * 创建时间：2020/6/2 5:42 下午
 * 创建人：zhang
 */
@Mapper
public interface PageResponseVoConverter {
    PageResponseVoConverter INSTANCE = Mappers.getMapper(PageResponseVoConverter.class);

    PageResponseVo domain2Vo(Page user);
}
