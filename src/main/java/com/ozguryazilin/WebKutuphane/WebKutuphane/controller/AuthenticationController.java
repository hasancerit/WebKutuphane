package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.AuthenticationService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@SessionAttributes("user")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/loginsuccess")
    public String loadUserToSession(Model model, Principal principal){
        User currentUser = userService.getUser(principal.getName());
        model.addAttribute("user",currentUser);
        return "redirect:/mainpage";
    }

    @GetMapping("/login")
    public String login(Model model,Principal principal) {
        if(principal != null){
            return "redirect:/mainpage";
        }
        return "login/login";
    }

    @GetMapping("/mainpage")
    public String mainpage(Model model){
        List<Book> books = bookService.allBooks();

        //Değişecek
        List<Integer> itr = new ArrayList<>();
        int y;
        if (books.size()%2==0) y=books.size()/2;
        else y = (books.size()/2)+1;

        int ind = 0;
        for(int i = 1; i <= y; i++){
            itr.add(ind);
            ind += 2;
        }
        model.addAttribute("itr",itr);
        //

        model.addAttribute("books",books);
        model.addAttribute("searching",new Searching());
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
