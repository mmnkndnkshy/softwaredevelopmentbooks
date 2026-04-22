package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.DiscountProviderEnum;
import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookGroup;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.PricingSummaryDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.helper.BookGroupingHelper;
import com.mmnkndn.kata.softwaredevelopmentbooks.helper.BookPricingCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookPricingService {

    public PricingSummaryDto getPricingSummary(List<BookDto> listOfBooks) {

        PricingSummaryDto pricingSummary = new PricingSummaryDto();

        Map<Integer, Integer> numberOfBooksMap = listOfBooks.stream().collect(Collectors.toMap(BookDto::getId, BookDto::getNoOfBooks));

        List<BookGroup> listOfBookGroups = BookGroupingHelper.getGroupedBooks(numberOfBooksMap, new ArrayList<>());

        listOfBookGroups.add(BookGroupingHelper.getRemainingBooksGroup(numberOfBooksMap));

        return BookPricingCalculator.calculate(listOfBookGroups);
    }

}
