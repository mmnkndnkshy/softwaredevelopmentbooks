package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/softwaredevelopmentbooks")
public class SoftwareDevelopmentBooksController {

    @GetMapping("/getbooks")
    public String getBooks(){
        return "Success";
    }
}
