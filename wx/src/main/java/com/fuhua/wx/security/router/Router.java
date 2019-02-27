package com.fuhua.wx.security.router;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Router {

    @GetMapping("/login")
    private String login() {
        return "/login.html";
    }
}
