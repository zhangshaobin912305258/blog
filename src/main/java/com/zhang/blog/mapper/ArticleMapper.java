package com.zhang.blog.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.zhang.blog.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface ArticleMapper extends BaseMapper<Article> {

    IPage<Article> listByPage(IPage<Article> pageRequest, @Param(Constants.WRAPPER) QueryWrapper<Article> queryWrapper);
}
