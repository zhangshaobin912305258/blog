package com.zhang.blog.config.exception;

import com.zhang.blog.vo.Result;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseException extends RuntimeException{

    private Result result;

    public BaseException(Result result) {
        this.result = result;
    }

}
