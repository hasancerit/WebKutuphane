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

    @GetMapping("/{id}")
    public String getAuthor(@PathVariable String id,Model model){
        model.addAttribute("author",authorService.getAuthor(id));
        return "author/authordetail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }
/*
    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable String id,Model model){
        model.addAttribute("book",bookService.getBook(id));
        model.addAttribute("action","update");
        bookService.deleteBook(id);
        return "book/addform";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return "redirect:/mainpage";
    }
 */
}
