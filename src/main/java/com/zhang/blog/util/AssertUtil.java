package com.zhang.blog.util;

import cn.hutool.core.util.StrUtil;
import com.zhang.blog.config.exception.BaseException;
import com.zhang.blog.vo.Result;

/**
 * 断言工具类
 */
public class AssertUtil {

    public static <T extends CharSequence> void notEmpty(T data, Result result) {
        if (StrUtil.isEmpty(data)) {
            throw new BaseException(result);
        }
    }

    public static void notNull(Object data, Result result) {
        if (data == null) {
            throw new BaseException(result);
        }
    }
}
