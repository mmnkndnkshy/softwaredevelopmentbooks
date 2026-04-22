package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookPricingServiceTest {
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final double BOOK_PRICE = 50.00;
    private static final double PRICE_OF_TWO_DISTINCT_BOOKS=95.00;

    @Autowired
    private BookPricingService bookPricingService;

    @Test
    @DisplayName("book pricing for a book should return 50")
    void bookPricingForABook_shouldReturnFifty() {
        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto bookDto = new BookDto(ONE, ONE);
        listOfBooks.add(bookDto);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks);

        assertEquals(BOOK_PRICE, actualPrice);
    }

    @ParameterizedTest
    @CsvSource({"1, 50.00", "2, 100.00", "3, 150.00", "4, 200.00", "5, 250.00"})
    @DisplayName("book pricing should return price based on quantity")
    void bookPricing_shouldReturnPriceBasedOnQuantity(int noOfBooks, double expectedPrice) {
        List<BookDto> listOfBooks = new ArrayList<BookDto>();

        BookDto bookDto = new BookDto(ONE, noOfBooks);
        listOfBooks.add(bookDto);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks);

        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    @DisplayName("book pricing should apply 5% discount on two distinct books")
    void bookPricing_shouldApplyFivePercentageDiscountOnTwoDistinctBooks() {
        List<BookDto> listOfBooks = new ArrayList<BookDto>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, ONE);
        listOfBooks.add(secondBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks);

        assertEquals(PRICE_OF_TWO_DISTINCT_BOOKS, actualPrice);
    }
}
