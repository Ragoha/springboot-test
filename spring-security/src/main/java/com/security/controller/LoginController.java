package com.security.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /* 로그인 Form 이동*/
    @GetMapping("/login")
    public String login(){
        return "/loginForm";
    }
}
