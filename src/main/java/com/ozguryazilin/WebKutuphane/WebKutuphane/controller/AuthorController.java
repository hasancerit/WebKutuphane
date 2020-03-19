package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
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

    @GetMapping("/add")
    public String addAuthor(Model model, @ModelAttribute("user") User user){
        model.addAttribute("author",new Author());
        model.addAttribute("user",user);
        model.addAttribute("action","add");
        return "author/addform";
    }

    @PostMapping("/add")
    public String handeAddAuthor(@ModelAttribute Author author){
        authorService.addAuthor(author);
        return "redirect:/author";
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
    public String deleteAuthor(@PathVariable String id){
        authorService.deleteAuthor(id);
        return "redirect:/author";
    }

    @GetMapping("/update/{id}")
    public String updateAuthor(@PathVariable String id,Model model){
        model.addAttribute("author",authorService.getAuthor(id));
        model.addAttribute("action","update");
        return "author/addform";
    }

    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute Author author){
        authorService.addAuthor(author);
        return "redirect:/author";
    }

    @GetMapping("/{id}/addbook")
    public String addBookToAuthor(Model model,@PathVariable String id){
        Book book = new Book();
        book.setAuthor(authorService.getAuthor(id));
        model.addAttribute("book",book);
        model.addAttribute("action","byauthor");
        return "book/addformap";
    }

}
