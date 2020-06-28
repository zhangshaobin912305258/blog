package com.zhang.blog.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhang.blog.constants.Const;
import com.zhang.blog.constants.ResultCode;
import com.zhang.blog.entity.Article;
import com.zhang.blog.entity.ArticleLabel;
import com.zhang.blog.entity.Label;
import com.zhang.blog.entity.User;
import com.zhang.blog.mapper.ArticleMapper;
import com.zhang.blog.service.ArticleLabelService;
import com.zhang.blog.service.ArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhang.blog.service.LabelService;
import com.zhang.blog.util.AssertUtil;
import com.zhang.blog.util.ListUtils;
import com.zhang.blog.util.SecurityUtils;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.converter.PageConverter;
import com.zhang.blog.vo.request.PageRequestVo;
import com.zhang.blog.vo.response.PageResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    private final PageConverter pageConverter;
    private final LabelService labelService;


    @Override
    public Result<PageResult> listByPage(PageRequestVo pageRequestVo) {
        String keyword = pageRequestVo.getKeyword();
        long labelId = pageRequestVo.getLabelId();
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
        return Result.ok(pageConverter.toDto(articlePage));
    }

    @Override
    public Result insert(Article article) {
        AssertUtil.notNull(article, Result.fail(ResultCode.PARAM_ERROR));
        Integer id = article.getId();
        AssertUtil.isTrue(id == null || id == 0, Result.fail(ResultCode.PARAM_ERROR));
        User user = SecurityUtils.getUser();
        AssertUtil.notNull(user, Result.fail(ResultCode.LOGIN_INFO_INVALID));
        article.setUserId(user.getId());
        LocalDateTime now = LocalDateTime.now();
        article.setCreatedAt(now);
        article.setCreatedName(user.getNickName());
        article.setUpdatedAt(now);
        saveOrUpdate(article);
        List<Label> labels = article.getLabels();
        labelService.saveOrUpdate(labels, article);
        return Result.ok();
    }

    @Override
    public Result modify(Article article) {
        AssertUtil.notNull(article, Result.fail(ResultCode.PARAM_ERROR));
        Integer id = article.getId();
        AssertUtil.isTrue(id != null && id > 0, Result.fail(ResultCode.PARAM_ERROR));
        Long userId = article.getUserId();
        User user = SecurityUtils.getUser();
        AssertUtil.notNull(user, Result.fail(ResultCode.LOGIN_INFO_INVALID));
        AssertUtil.isTrue(user.getId().equals(userId), Result.fail(ResultCode.UNAUTHORIZED));
        LocalDateTime now = LocalDateTime.now();
        article.setUpdatedAt(now);
        saveOrUpdate(article);
        List<Label> labels = article.getLabels();
        labelService.saveOrUpdate(labels, article);
        return Result.ok();
    }

    @Override
    public Result delete(Integer id) {
        AssertUtil.isTrue(id != null && id > 0, Result.fail(ResultCode.PARAM_ERROR));
        Article article = getById(id);
        AssertUtil.notNull(article, Result.fail(ResultCode.PARAM_ERROR, Const.ARTICLE_NOT_FOUND));

        return null;
    }
}
