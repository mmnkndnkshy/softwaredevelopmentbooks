package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookPricingCalculator {

    private static final int ONE = 1;

    private static final int HUNDRED = 100;

    private final DiscountResolver discountResolver;

    private final BookCatalog bookCatalog;

    public double calculateGroupPrice(int size) {

        double pricePerBook = bookCatalog.getPrice(ONE);
        double total = size * pricePerBook;

        int discount = discountResolver.getDiscountPercentage(size);

        return total - (total * discount / HUNDRED);
    }
}
