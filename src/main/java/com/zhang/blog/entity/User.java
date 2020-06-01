package com.zhang.blog.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * <p>
 *
 * </p>
 *
 * @author zhang
 * @since 2020-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

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
     * 加密盐
     */
    private String salt;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 状态 0正常 -1异常
     */
    private Integer status;

    @TableField(exist = false)
    private List<String> roles;

    /** 权限列表 */
    @TableField(exist = false)
    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    /** 用户账号是否过期 */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /** 用户账号是否被锁定 */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /** 用户密码是否过期 */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /** 用户是否可用 */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
