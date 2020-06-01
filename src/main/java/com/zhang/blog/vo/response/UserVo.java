package com.zhang.blog.vo.response;

import com.baomidou.mybatisplus.annotation.TableField;
import com.zhang.blog.entity.Permission;
import com.zhang.blog.entity.Role;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 类描述：
 * 创建时间：2020/6/1 10:44 上午
 * 创建人：zhang
 */
@Data
public class UserVo implements Serializable {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 创建时间
     */
    private LocalDateTime createdAt;

    /**
     * 修改时间
     */
    private LocalDateTime updatedAt;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 状态 0正常 -1异常
     */
    private Integer status;

}
