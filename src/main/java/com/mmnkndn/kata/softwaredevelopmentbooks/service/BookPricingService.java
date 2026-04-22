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
    private static final int TWO_DISTINCT_BOOKS=2;
    private static final int HUNDRED = 100;
    private static final int ZERO_PERCENTAGE =0;
    private static final int FIVE_PERCENTAGE =5;

    public Double bookPricing(List<BookDto> listOfBooks) {

        Map<Integer, Double> bookIdPriceMap = Arrays.stream(SoftwareDevelopmentBook.values()).collect(Collectors.toMap(SoftwareDevelopmentBook::getId, SoftwareDevelopmentBook::getPrice));

        long distinctBooks = listOfBooks.stream().mapToLong(BookDto::getId).distinct().count();
        int discountPercentage = ((distinctBooks == TWO_DISTINCT_BOOKS)? FIVE_PERCENTAGE: ZERO_PERCENTAGE);

        double actualPrice = listOfBooks.stream().mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getNoOfBooks()).sum();
        double dicountPrice = ((actualPrice * discountPercentage)/HUNDRED);

        return (actualPrice - dicountPrice);
    }
}
