package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
@SessionAttributes("user")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public String getAuthors(@ModelAttribute User user, Model model){
        model.addAttribute("authors",authorService.allAuthors());
        model.addAttribute("searching",new Searching());
        return "author/authors";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Searching searching,Model model){
        List<Author> authors = authorService.searchBook(searching.getAction(),searching.getSearch());
        model.addAttribute("authors",authors);
        return "author/authors";
    }
}
