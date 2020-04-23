package com.lagou.edu.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: zhuhf
 * @Date: 2020/4/23 11:08 上午
 */
public class AuthInterceptor implements HandlerInterceptor {
    @Value("${login.sessionName}")
    private String loginSessionName;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        String s = (String) session.getAttribute(loginSessionName);
        if (s != null) {
            System.out.println("登录验证通过");
            return true;
        } else {
            System.out.println("请先登录");
            request.setAttribute("msg", "请先登录");
            request.getRequestDispatcher("/WEB-INF/jsp/homepage.jsp").forward(request, response);
            return false;
        }
    }
}
