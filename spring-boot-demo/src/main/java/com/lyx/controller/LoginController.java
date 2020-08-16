package com.lyx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {

    @PostMapping("/user/login")
    public String login(@RequestParam String username, @RequestParam String password , HashMap<String, Object> hashMap, HttpSession httpSession){

        if (!StringUtils.isEmpty(username)&&"123456".equals(password)){
            httpSession.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            hashMap.put("msg","用户登录失败");
            return "login";
        }
    }

}
