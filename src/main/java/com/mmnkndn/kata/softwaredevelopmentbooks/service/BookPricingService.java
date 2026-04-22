package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookPricingService {

    public Double bookPricing(List<BookDto> listOfBooks) {
        Map<Integer, Double> bookIdPriceMap = Arrays.stream(SoftwareDevelopmentBook.values()).collect(Collectors.toMap(SoftwareDevelopmentBook::getId, SoftwareDevelopmentBook::getPrice));
        return listOfBooks.stream().mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getNoOfBooks()).sum();
    }
}
