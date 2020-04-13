package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.BookService;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    public String handleAddBook(@ModelAttribute Book book,Model model){
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

    @PostMapping("/updateimage/{id}")
    public String updateBook(@RequestParam(value = "file") MultipartFile image,@PathVariable String id){
        try {
            bookService.updateImage(id,image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "redirect:/book/"+id;
    }

    /**Ajax Handler**/
    @PostMapping("/addimage")
    public String addimage(@ModelAttribute Book book,@RequestParam(value = "file") MultipartFile image){
        try {
            book.setBookImage(image.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        bookService.addBook(book);
        return "redirect:/mainpage";
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public String sameUsername(Exception e, HttpServletRequest request){
        if(request.getServletPath().equalsIgnoreCase("/book/update")) {
            return "redirect:" + request.getRequestURI()+"/"+request.getParameter("id")+"?error";
        }else if(request.getServletPath().equalsIgnoreCase("/book/addimage")){
            System.out.println("add");
            return "redirect:/book/add?error";
        }
        return "redirect:"+request.getRequestURI()+"?error";
    }
}
