package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.DiscountProvidedEnum;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookPricingService {
    private static final int HUNDRED = 100;
    private static final int ZERO_PERCENTAGE = 0;

    public Double bookPricing(List<BookDto> listOfBooks) {

        Map<Integer, Double> bookIdPriceMap = Arrays.stream(SoftwareDevelopmentBook.values()).collect(Collectors.toMap(SoftwareDevelopmentBook::getId, SoftwareDevelopmentBook::getPrice));

        long distinctBooks = listOfBooks.stream().mapToLong(BookDto::getId).distinct().count();

        double actualPrice = listOfBooks.stream().mapToDouble(book -> bookIdPriceMap.get(book.getId()) * book.getNoOfBooks()).sum();

        double dicountPrice = ((actualPrice * getDiscountPercentage(distinctBooks)) / HUNDRED);

        return (actualPrice - dicountPrice);
    }

    private int getDiscountPercentage(long distinctBooks) {
        Optional<DiscountProvidedEnum> discount = Arrays.stream(DiscountProvidedEnum.values()).sorted(Comparator.reverseOrder()).filter(discountProvidedEnum -> discountProvidedEnum.getNumberOfDistinctBooks() <= distinctBooks).findFirst();
        return (discount.isPresent()) ? discount.get().getDiscountPercentage() : ZERO_PERCENTAGE;
    }
}
