package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
@SessionAttributes("user")
public class BookController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String addBook(Model model, @ModelAttribute("user") User user){
        model.addAttribute("book",new Book());
        model.addAttribute("user",user);
        return "book/addform";
    }

    @PostMapping("/add")
    public String handleAddBook(@ModelAttribute Book book, @SessionAttribute("user") User user){
        bookService.addBook(book);
        return "redirect:/mainpage";
    }
}
