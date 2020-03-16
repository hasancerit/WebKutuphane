package com.ozguryazilin.WebKutuphane.WebKutuphane.controller;

import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Author;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Publisher;
import com.ozguryazilin.WebKutuphane.WebKutuphane.model.Searching;
import com.ozguryazilin.WebKutuphane.WebKutuphane.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return "main/publishers";
    }

    @PostMapping("/search")
    public String search(@ModelAttribute Searching searching, Model model){
        List<Publisher> publishers = publisherService.searchBook(searching.getAction(),searching.getSearch());
        model.addAttribute("publishers",publishers);
        return "main/publishers";
    }
}
