package com.zhang.blog.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 类描述：
 * 创建时间：2020/6/2 10:59 下午
 * 创建人：zhang
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleMqIndexMessage implements Serializable {

    public static final String CREATE_OR_UPDATE = "create_update";
    public static final String REMOVE = "remove";

    private Long articleId;
    private String type;

}
