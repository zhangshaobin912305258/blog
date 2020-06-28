package com.zhang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.blog.entity.Article;
import com.zhang.blog.entity.ArticleLabel;
import com.zhang.blog.entity.Label;
import com.zhang.blog.mapper.LabelMapper;
import com.zhang.blog.service.ArticleLabelService;
import com.zhang.blog.service.LabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.blog.util.ListUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
@Service
@RequiredArgsConstructor
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements LabelService {

    private final ArticleLabelService articleLabelService;

    @Override
    public Label getByLabelName(String labelName) {
        if (StringUtils.isEmpty(labelName)) {
            return null;
        }
        LambdaQueryWrapper<Label> queryWrapper = new LambdaQueryWrapper<>();
        return getOne(queryWrapper.eq(Label::getLabelName, labelName));
    }

    @Override
    public ArticleLabel saveOrUpdate(ArticleLabel articleLabel) {
        if (articleLabel == null) {
            return null;
        }
        articleLabelService.saveOrUpdate(articleLabel);
        return articleLabel;
    }

    @Override
    public void saveOrUpdate(List<Label> labels, Article article) {
        if (ListUtils.isNotEmpty(labels) && article != null) {
            Integer articleId = article.getId();
            List<ArticleLabel> articleLabels = listArticleLabels(articleId);
            if (ListUtils.isNotEmpty(articleLabels)) {
                articleLabelService.removeByIds(articleLabels.stream().map(ArticleLabel::getId).collect(Collectors.toList()));
            }
            for (Label label : labels) {
                String labelName = label.getLabelName();
                Label labelDb = getByLabelName(labelName);
                if (labelDb == null) {
                    labelDb = Label.builder()
                            .id(0)
                            .labelName(labelName)
                            .createdAt(LocalDateTime.now())
                            .build();
                    saveOrUpdate(labelDb);
                }
                ArticleLabel articleLabel = ArticleLabel.builder()
                        .articleId(articleId)
                        .labelId(labelDb.getId())
                        .build();
                saveOrUpdate(articleLabel);
            }
        }
    }

    public List<ArticleLabel> listArticleLabels(Integer articleId) {
        return articleLabelService.listByArticleId(articleId);
    }
}
