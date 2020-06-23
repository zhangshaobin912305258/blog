package com.zhang.blog.service;

import com.zhang.blog.entity.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.PageRequestVo;
import com.zhang.blog.vo.response.PageResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhang
 * @since 2020-05-29
 */
public interface ArticleService extends IService<Article> {

    Result<PageResult> listByPage(PageRequestVo pageRequestVo);
}
