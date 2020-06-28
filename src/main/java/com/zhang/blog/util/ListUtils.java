package com.zhang.blog.util;

import cn.hutool.core.collection.ListUtil;

import java.util.List;

public class ListUtils extends ListUtil {

    public static <T> boolean isNotEmpty(List<T> list) {
        return list != null && !list.isEmpty();
    }

    public static <T> boolean isEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }
}
