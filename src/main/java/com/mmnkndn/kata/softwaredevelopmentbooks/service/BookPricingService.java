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

        long distinctBooks = listOfBooks.stream().mapToLong(BookDto::getId).distinct().count();
        int discountPercentage = ((distinctBooks == 2)? 5: 0);

        double actualPrice = listOfBooks.stream().mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getNoOfBooks()).sum();
        double dicountPrice = ((actualPrice * discountPercentage)/100);

        return (actualPrice - dicountPrice);
    }
}
