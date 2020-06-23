package com.zhang.blog.vo.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.blog.vo.response.PageResult;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

/**
 * 类描述：
 * 创建时间：2020/6/23 4:48 下午
 * 创建人：zhang
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PageMapper extends BaseMapper<PageResult, Page> {
}
