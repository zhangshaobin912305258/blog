package com.zhang.blog.vo.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 创建时间：2020/6/23 4:49 下午
 * 创建人：zhang
 */
@Data
public class PageResult implements Serializable {
    private long current;
    private long size;
    private long total;
    private long pages;
    private List<?> records;
    private boolean hasNext;
    private boolean hasPrevious;
}
