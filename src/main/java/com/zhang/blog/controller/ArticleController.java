package com.zhang.blog.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zhang.blog.entity.Article;
import com.zhang.blog.service.ArticleService;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.PageRequestVo;
import com.zhang.blog.vo.response.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 类描述：
 * 创建时间：2020/6/2 4:22 下午
 * 创建人：zhang
 */
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/listByPage")
    public Result<PageResult> listByPage(@RequestBody @Valid PageRequestVo pageRequestVo) {
        return articleService.listByPage(pageRequestVo);
    }
}
