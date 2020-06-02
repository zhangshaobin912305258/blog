package com.zhang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.blog.converter.PageResponseVoConverter;
import com.zhang.blog.entity.Article;
import com.zhang.blog.entity.ArticleLabel;
import com.zhang.blog.mapper.ArticleMapper;
import com.zhang.blog.service.ArticleLabelService;
import com.zhang.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.PageRequestVo;
import com.zhang.blog.vo.response.PageResponseVo;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements ArticleService {
    private final ArticleLabelService articleLabelService;

    @Override
    public Result<PageResponseVo> listByPage(PageRequestVo pageRequestVo) {
        String keyword = pageRequestVo.getKeyword();
        int labelId = pageRequestVo.getLabelId();
        long page = pageRequestVo.getCurrent();
        long pageSize = pageRequestVo.getSize();
        IPage<Article> pageRequest = new Page<>(page, pageSize);
        List<Integer> articleIds = new ArrayList<>();
        if (labelId > 0) {
            List<ArticleLabel> articleLabels = articleLabelService.listByLabelId(labelId);
            if (articleLabels != null && !articleLabels.isEmpty()) {
                articleIds = articleLabels.stream()
                        .map(ArticleLabel::getArticleId)
                        .collect(Collectors.toList());
            }
        }
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(!articleIds.isEmpty(), "id", articleIds);
        queryWrapper.like(StringUtils.isNotEmpty(keyword), "title", keyword.replaceAll("", "%"));
        Page<Article> articlePage = (Page<Article>) baseMapper.listByPage(pageRequest, queryWrapper);
        PageResponseVo pageResponseVo = PageResponseVoConverter.INSTANCE.domain2Vo(articlePage);
        pageResponseVo.setCurrent(articlePage.getCurrent());
        pageResponseVo.setSize(articlePage.getSize());
        return Result.ok(pageResponseVo);
    }
}
