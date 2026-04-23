package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class BookCatalog {

    private static final Map<Integer, Double> PRICE_MAP = Arrays.stream(SoftwareDevelopmentBook.values()).collect(Collectors.toMap(SoftwareDevelopmentBook::getId, SoftwareDevelopmentBook::getPrice));

    public double getPrice(int bookId) {
        return PRICE_MAP.getOrDefault(bookId, 0.0);
    }
}
