package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.Book;
import com.mmnkndn.kata.softwaredevelopmentbooks.service.SoftwareDevelopmentBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/softwaredevelopmentbooks")
public class SoftwareDevelopmentBooksController {

    @Autowired
    private SoftwareDevelopmentBooksService softwareDevelopmentBooksService;

    @GetMapping("/getbooks")
    public List<Book> getBooks(){
        return softwareDevelopmentBooksService.getBooks();
    }
}
