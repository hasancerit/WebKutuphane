package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login(Model model) {
        System.out.println("LOGIN METHOD");
        return "login/login";
    }

    @GetMapping("/test")
    public String test() {
        return "login/test";
    }
}
