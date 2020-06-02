package com.zhang.blog.util;

import cn.hutool.crypto.SecureUtil;
import com.zhang.blog.entity.User;

import java.util.Random;

/**
 * 密码加密
 *
 * @author Louis
 * @date Jan 13, 2019
 */
public class PasswordEncoder {

    private static final int saltLen = 15;//盐长度

    /*
     * 对password进行MD5加盐加密，返回加密过的password,并存储盐salt
     */
    public static User encrypt(User user) {
        Random random = new Random();
        String str = "qwertyuiopasdfghjklzxcvbnm1234567890";
        String salt = "";
        for (int i = 0; i < saltLen; i++) {
            salt += String.valueOf(str.charAt(random.nextInt(str.length())));//从str中随机获取字符生成长度为saltLen的加密盐
        }
        String passwordSalt = user.getPassword() + salt;//将密码和盐拼接到一起
        try {
            String md5 = SecureUtil.md5(passwordSalt);
            user.setPassword(md5);
            user.setSalt(salt);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean matches(String password, User user) {
        String passwordSalt = password + user.getSalt();//将密码和盐拼接到一起
        try {
            String md5 = SecureUtil.md5(passwordSalt);
            return md5.equalsIgnoreCase(user.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean matches(String salt, String password, String presentedPassword) {
        User user = User.builder().password(password).salt(salt).build();
        return matches(presentedPassword, user);
    }


    /*public static void main(String[] args) {
        User encrypt = encrypt(User.builder().username("admin").password("admin").build());
        System.out.println(encrypt);
    }*/

}
