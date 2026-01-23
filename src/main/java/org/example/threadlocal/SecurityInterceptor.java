package org.example.threadlocal;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.common.entity.User;
import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 安全拦截器，在请求处理之前和之后处理用户信息的存储和清除
 */
public class SecurityInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.从header获取用户信息
        String token = request.getHeader("Authorization");
        User user = userService.parseToken(token);
        //2.将用户信息存入threadlocal
        UserContextHolder.setUser(user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        //清除用户信息，防止内存泄漏
        UserContextHolder.clear();
    }

    //模拟一个用户服务类
    static class UserService {
        public User parseToken(String token) {
            return new User();
        }
    }
}
