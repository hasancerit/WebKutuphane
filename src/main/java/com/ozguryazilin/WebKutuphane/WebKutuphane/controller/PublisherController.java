package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Book;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.User;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/publisher")
@SessionAttributes("user")
public class PublisherController {
    @Autowired
    private PublisherService publisherService;

    @GetMapping
    public String getPublishers(Model model){
        model.addAttribute("publishers",publisherService.findAll());
        model.addAttribute("searching",new Searching());
        return "publisher/publishers";
    }

    @GetMapping("/add")
    public String addPublisher(Model model){
        model.addAttribute("publisher",new Publisher());
        model.addAttribute("action","add");
        return "publisher/addform";
    }

    @PostMapping("/add")
    public String handeAddAuthor(@ModelAttribute Publisher author){
        publisherService.addPublisher(author);
        return "redirect:/publisher";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Searching searching, Model model){
        List<Publisher> publishers = publisherService.searchBook(searching.getAction(),searching.getSearch());
        model.addAttribute("publishers",publishers);
        return "publisher/publishers";
    }

    @GetMapping("/{id}")
    public String getPublisher(@PathVariable String id,Model model){
        model.addAttribute("publisher",publisherService.getPublisher(id));
        return "publisher/publisherdetail";
    }

    @GetMapping("/delete/{id}")
    public String deletePublisher(@PathVariable String id){
        publisherService.deletePublisher(id);
        return "redirect:/publisher";
    }

    @GetMapping("/update/{id}")
    public String updatePublisher(@PathVariable String id,Model model){
        model.addAttribute("publisher",publisherService.getPublisher(id));
        model.addAttribute("action","update");
        return "publisher/addform";
    }

    @PostMapping("/update")
    public String updatePublisher(@ModelAttribute Publisher publisher){
        publisherService.addPublisher(publisher);
        return "redirect:/publisher";
    }

    @GetMapping("/{id}/addbook")
    public String addBookToPublisher(Model model,@PathVariable String id){
        Book book = new Book();
        book.setPublisher(publisherService.getPublisher(id));
        model.addAttribute("book",book);
        model.addAttribute("action","bypublisher");
        return "book/addformap";
    }

}
