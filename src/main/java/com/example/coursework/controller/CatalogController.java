package com.example.coursework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class CatalogController {

    @GetMapping()
    public String getMain() {
        return "index";
    }

    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/commodes")
    public String getCommodes() {
        return "commodes";
    }

    @GetMapping("/stelagi")
    public String getStelagi() {
        return "stelagi";
    }

    @GetMapping("/wardrobes")
    public String getWardrobes() {
        return "wardrobes";
    }
}
