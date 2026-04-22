package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.Book;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.PricingSummaryDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.service.BookPricingService;
import com.mmnkndn.kata.softwaredevelopmentbooks.service.SoftwareDevelopmentBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${softwaredevelopmentbooks.controller.path}")
public class SoftwareDevelopmentBooksController {

    @Autowired
    private SoftwareDevelopmentBooksService softwareDevelopmentBooksService;

    @Autowired
    private BookPricingService bookPricingService;

    @GetMapping("${softwaredevelopmentbooks.endpoints.getbooks}")
    public List<Book> getBooks() {

        return softwareDevelopmentBooksService.getBooks();

    }

    @PostMapping("${softwaredevelopmentbooks.endpoints.getpricingsummary}")
    public PricingSummaryDto getPricingSummary(@RequestBody List <BookDto> listOfBooks) {
        return bookPricingService.getPricingSummary(listOfBooks);
    }
}
