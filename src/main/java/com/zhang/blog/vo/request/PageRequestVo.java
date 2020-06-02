package com.zhang.blog.vo.request;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * 类描述：
 * 创建时间：2020/6/2 5:09 下午
 * 创建人：zhang
 */
@Data
public class PageRequestVo implements Serializable {

    @Min(value = 1)
    private long current = 1;
    @Min(value = 10)
    @Max(value = 50)
    private long size = 10;
    private String keyword;
    private int labelId;
}
