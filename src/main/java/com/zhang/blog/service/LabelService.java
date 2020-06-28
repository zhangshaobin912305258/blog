package com.zhang.blog.service;

import com.zhang.blog.entity.Article;
import com.zhang.blog.entity.ArticleLabel;
import com.zhang.blog.entity.Label;
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
public interface LabelService extends IService<Label> {

    Label getByLabelName(String labelName);

    ArticleLabel saveOrUpdate(ArticleLabel articleLabel);

    void saveOrUpdate(List<Label> labels, Article article);

    List<ArticleLabel> listArticleLabels(Integer articleId);
}
