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

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable String id){
        bookService.deleteBook(id);
        return "redirect:/mainpage";
    }

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
}
