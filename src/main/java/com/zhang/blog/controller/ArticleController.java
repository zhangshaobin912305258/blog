package com.zhang.blog.controller;

import com.zhang.blog.entity.Article;
import com.zhang.blog.service.ArticleService;
import com.zhang.blog.vo.Result;
import com.zhang.blog.vo.request.PageRequestVo;
import com.zhang.blog.vo.response.PageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 插入文章
     * @param article
     * @return
     */
    @PostMapping("/insert")
    public Result insert(@RequestBody Article article) {
        return articleService.insert(article);
    }

    /**
     * 修改文章
     * @param article
     * @return
     */
    @PostMapping("/modify")
    public Result modify(@RequestBody Article article) {
        return articleService.modify(article);
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Integer id) {
        return articleService.delete(id);
    }
}
