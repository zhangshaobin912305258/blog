package com.zhang.blog.service;

import com.zhang.blog.entity.ArticleLabel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface ArticleLabelService extends IService<ArticleLabel> {

    List<ArticleLabel> listByLabelId(long labelId);
}
