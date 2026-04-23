package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.PricingSummaryDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.helper.BookCatalog;
import com.mmnkndn.kata.softwaredevelopmentbooks.helper.BookPricingEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookPricingService {

    private final BookPricingEngine bookPricingEngine;

    private final BookCatalog bookCatalog;

    public PricingSummaryDto getPricingSummary(List<BookDto> books) {

        Map<Integer, Integer> bookMap = books.stream()
                .collect(Collectors.toMap(
                        BookDto::getId,
                        BookDto::getNoOfBooks,
                        Integer::sum
                ));

        double finalPrice = bookPricingEngine.getOptimalPrice(bookMap);

        double pricePerBook = bookCatalog.getPrice(1);

        int totalBooks = bookMap.values().stream()
                .mapToInt(Integer::intValue)
                .sum();

        double actualPrice = totalBooks * pricePerBook;

        double totalDiscount = actualPrice - finalPrice;

        return PricingSummaryDto.of(actualPrice, totalDiscount, finalPrice);
    }

}
