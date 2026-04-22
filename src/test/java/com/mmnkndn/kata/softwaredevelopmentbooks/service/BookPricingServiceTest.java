package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookPricingServiceTest {
    private static final int BOOK_ID = 1;
    private static final double BOOK_PRICE = 50.00;

    @Autowired
    private BookPricingService bookPricingService;

    @Test
    @DisplayName("book pricing for a book should return 50")
    void bookPricingForABook_shouldReturnFifty() {
        Double actualPrice = bookPricingService.bookPricing(BOOK_ID);
        assertEquals(BOOK_PRICE, actualPrice);
    }
}
