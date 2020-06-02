package com.zhang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zhang.blog.entity.ArticleLabel;
import com.zhang.blog.mapper.ArticleLabelMapper;
import com.zhang.blog.service.ArticleLabelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
@Service
public class ArticleLabelServiceImpl extends ServiceImpl<ArticleLabelMapper, ArticleLabel> implements ArticleLabelService {

    @Override
    public List<ArticleLabel> listByLabelId(long labelId) {
        LambdaQueryWrapper<ArticleLabel> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(labelId > 0, ArticleLabel::getLabelId, labelId);
        return list(queryWrapper);
    }
}
