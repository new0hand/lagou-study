package com.lagou.edu.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Author: zhuhf
 * @Date: 2020/4/23 11:09 上午
 */
@Controller
public class AuthController {
    @Value("${canLogin.username}")
    private String canLoginUsername;

    @Value("${canLogin.password}")
    private String canLoginPassword;

    @Value("${login.sessionName}")
    private String loginSessionName;

    @GetMapping("/homepage")
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("homepage");
        return modelAndView;
    }

    @GetMapping("/login")
    public String login(String username, String password, HttpSession httpSession) {
        if (canLoginUsername.equals(username) && canLoginPassword.equals(password)) {
            System.out.println("username:" + username + ",password:" + password);
            httpSession.setAttribute(loginSessionName, username + ":" + password);
            return "redirect:/resume/list";
        } else {
            return "redirect:/homepage";
        }
    }

    @GetMapping("logout")
    public String logout(HttpSession httpSession) {
        httpSession.invalidate();

        return "redirect:/homepage";
    }
}
