package com.zhang.blog.controller;

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
 * 文章api
 */
@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    /**
     * 分页查询文章列表
     * @param pageRequestVo
     * @return
     */
    @PostMapping("/listByPage")
    public Result<PageResult> listByPage(@RequestBody @Valid PageRequestVo pageRequestVo) {
        return articleService.listByPage(pageRequestVo);
    }
}
