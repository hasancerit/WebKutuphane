package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/book")
@SessionAttributes("user")
public class BookController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book",new Book());
        model.addAttribute("action","add");
        return "book/addform";
    }

    @PostMapping("/add")
    public String handleAddBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return "redirect:/mainpage";
    }

    @PostMapping("/search")
    public String searchBook(@ModelAttribute Searching searching,Model model){
        List<Book> books = bookService.searchBook(searching.getAction(),searching.getSearch());
        model.addAttribute("books",books);
        return "main/mainpage";
    }

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable String id,Model model){
        model.addAttribute("book",bookService.getBook(id));
        return "book/bookdetail";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        bookService.deleteBook(id);
        return "redirect:/mainpage";
    }

    @GetMapping("/update/{id}")
    public String updateBook(@PathVariable String id,Model model){
        model.addAttribute("book",bookService.getBook(id));
        model.addAttribute("action","update");
        return "book/addform";
    }

    @PostMapping("/update")
    public String updateBook(@ModelAttribute Book book){
        bookService.addBook(book);
        return "redirect:/mainpage";
    }
}
