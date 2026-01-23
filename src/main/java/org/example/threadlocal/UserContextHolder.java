package org.example.threadlocal;

import org.example.common.entity.User;

/**
 * 将用户信息存入threadlocal，方便在任何地方获取用户信息
 */
public class UserContextHolder {
    //用threadlocal存储用户信息
    private static final ThreadLocal<User> USER_CONTEXT = new ThreadLocal<>();

    //存入用户信息
    public static void setUser(User user){
        USER_CONTEXT.set(user);
    }

    //获取数据信息
    public static User getUser(){
        return USER_CONTEXT.get();
    }

    //必须清除用户信息,防止内存泄漏
    public static void clear(){
        USER_CONTEXT.remove();
    }
}
