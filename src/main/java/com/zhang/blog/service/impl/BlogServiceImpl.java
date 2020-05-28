package com.zhang.blog.service.impl;

import com.zhang.blog.entity.Blog;
import com.zhang.blog.mapper.BlogMapper;
import com.zhang.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhang
 * @since 2020-05-28
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
