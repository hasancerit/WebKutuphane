package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.AuthenticationService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
@SessionAttributes("user")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        return "login/login";
    }

    @GetMapping("/mainpage")
    public String mainpage(Model model, Principal principal){
        System.out.println(principal.getName());
        User currentUser = userService.getUser(principal.getName());
        System.out.println(currentUser.getUsername());
        model.addAttribute("user",currentUser);
        return "main/mainpage";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("action","user");
        return "login/signup";
    }

    @GetMapping("/admin/signup")
    public String signupAdmin(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("action","admin");
        return "login/signup";
    }

    @PostMapping("/signup")
    public String handleSignupUser(@ModelAttribute User user) {
        authenticationService.handleSignupUser(user);
        return "login/login";
    }

    @PostMapping("/admin/signup")
    public String handleSignupAdmin(@ModelAttribute User user) {
        authenticationService.handleSignupAdmin(user);
        return "login/login";
    }

    @GetMapping("/test")
    public String test() {
        return "main/mainpage";
    }
}
