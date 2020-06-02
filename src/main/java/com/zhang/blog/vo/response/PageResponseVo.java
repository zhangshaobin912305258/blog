package com.zhang.blog.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 类描述：
 * 创建时间：2020/6/2 5:13 下午
 * 创建人：zhang
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponseVo implements Serializable {

    private long current;
    private long size;
    private long total;
    private long pages;
    private List<?> records;
    private boolean hasNext;
    private boolean hasPrevious;
}
