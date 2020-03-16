package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public String addBook(Model model, @ModelAttribute("user") User user){
        model.addAttribute("book",new Book());
        model.addAttribute("user",user);
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
        return "main/mainpage";
    }

    @GetMapping("/{id}")
    public String getDetailPage(@PathVariable String id,Model model){
        model.addAttribute("book",bookService.getBook(id));
        return "book/bookdetail";
    }
}
