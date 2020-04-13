package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.AuthenticationService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/login")
    public String login(Principal principal) {
        if(principal != null){
            return "redirect:/mainpage";
        }
        return "login/login";
    }

    @GetMapping("/mainpage")
    public String mainpage(Model model,@SessionAttribute("user") User user){
        model.addAttribute("books",bookService.allBooks());
        model.addAttribute("searching",new Searching());
        model.addAttribute("user",user);
        return "main/mainpage";
    }
    @GetMapping("/")
    public String mainpage(){
        return "redirect:/mainpage";
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
        return "redirect:/login";
    }

    @PostMapping("/admin/signup")
    public String handleSignupAdmin(@ModelAttribute User user) {
        authenticationService.handleSignupAdmin(user);
        return "redirect:/login";
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public String sameUsername(Exception e){
        return "redirect:/signup?error";
    }
}
