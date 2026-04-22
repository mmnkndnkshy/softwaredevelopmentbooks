package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.PricingSummaryDto;
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
    private static final int THREE = 3;
    private static final int FOUR = 4;
    private static final int FIVE = 5;
    private static final double BOOK_PRICE = 50.00;
    private static final double PRICE_OF_ONE_DISTINCT_BOOK = 50.00;
    private static final double PRICE_OF_TWO_DISTINCT_BOOKS = 95.00;
    private static final double PRICE_OF_THREE_DISTINCT_BOOKS = 135.00;
    private static final double PRICE_OF_FOUR_DISTINCT_BOOKS = 160.00;
    private static final double PRICE_OF_FIVE_DISTINCT_BOOKS = 187.50;
    private static final double PRICE_OF_THREE_BOOKS_AFTER_APPLY_DISCOUNT_FOR_TWO_DISTINCT_BOOKS = 145.00;
    private static final double PRICE_OF_NINE_BOOKS_AFTER_APPLY_DISCOUNT_FOR_DISTINCT_BOOKS = 372.50;
    private static final double PRICE_OF_NINE_BOOKS_WITHOUT_DISCOUNT = 450.00;
    private static final double PRICE_OF_NINE_BOOKS_WITH_DISCOUNT = 77.50;

    @Autowired
    private BookPricingService bookPricingService;

    @Test
    @DisplayName("book pricing for a book should return 50")
    void bookPricingForABook_shouldReturnFifty() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto bookDto = new BookDto(ONE, ONE);
        listOfBooks.add(bookDto);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(BOOK_PRICE, actualPrice);
    }

    @ParameterizedTest
    @CsvSource({"1, 50.00", "2, 100.00", "3, 150.00", "4, 200.00", "5, 250.00"})
    @DisplayName("book pricing should return price based on quantity")
    void bookPricing_shouldReturnPriceBasedOnQuantity() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto bookDto = new BookDto(ONE, ONE);
        listOfBooks.add(bookDto);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_ONE_DISTINCT_BOOK, actualPrice);
    }

    @Test
    @DisplayName("book pricing should apply 5% discount on two distinct books")
    void bookPricing_shouldApplyFivePercentageDiscountOnTwoDistinctBooks() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, ONE);
        listOfBooks.add(secondBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_TWO_DISTINCT_BOOKS, actualPrice);
    }

    @Test
    @DisplayName("book pricing should apply 10% discount on three distinct books")
    void bookPricing_shouldApplyTenPercentageDiscountOnThreeDistinctBooks() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, ONE);
        listOfBooks.add(secondBook);

        BookDto thirdBook = new BookDto(THREE, ONE);
        listOfBooks.add(thirdBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_THREE_DISTINCT_BOOKS, actualPrice);

    }

    @Test
    @DisplayName("book pricing should apply 20% discount on four distinct books")
    void bookPricing_shouldApplyTwentyPercentageDiscountOnFourDistinctBooks() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, ONE);
        listOfBooks.add(secondBook);

        BookDto thirdBook = new BookDto(THREE, ONE);
        listOfBooks.add(thirdBook);

        BookDto fourthBook = new BookDto(FOUR, ONE);
        listOfBooks.add(fourthBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_FOUR_DISTINCT_BOOKS, actualPrice);

    }

    @Test
    @DisplayName("book pricing should apply 25% discount on five distinct books")
    void bookPricing_shouldApplyTwentyFivePercentageDiscountOnFiveDistinctBooks() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, ONE);
        listOfBooks.add(secondBook);

        BookDto thirdBook = new BookDto(THREE, ONE);
        listOfBooks.add(thirdBook);

        BookDto fourthBook = new BookDto(FOUR, ONE);
        listOfBooks.add(fourthBook);

        BookDto fifthBook = new BookDto(FIVE, ONE);
        listOfBooks.add(fifthBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_FIVE_DISTINCT_BOOKS, actualPrice);

    }

    @Test
    @DisplayName("book pricing should apply 5% discount on only two distinct books")
    void bookPricing_shouldApplyFivePercentageDiscountOnlyForTwoDistinctBooks() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, TWO);
        listOfBooks.add(secondBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_THREE_BOOKS_AFTER_APPLY_DISCOUNT_FOR_TWO_DISTINCT_BOOKS, actualPrice);

    }

    @Test
    @DisplayName("book pricing should apply discount to all distinct books")
    void bookPricing_shouldApplyDiscountToAllDistinctBooks() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, TWO);
        listOfBooks.add(secondBook);

        BookDto thirdBook = new BookDto(THREE, THREE);
        listOfBooks.add(thirdBook);

        BookDto fourthBook = new BookDto(FOUR, TWO);
        listOfBooks.add(fourthBook);

        BookDto fifthBook = new BookDto(FIVE, ONE);
        listOfBooks.add(fifthBook);

        Double actualPrice = bookPricingService.bookPricing(listOfBooks).getFinalPrice();

        assertEquals(PRICE_OF_NINE_BOOKS_AFTER_APPLY_DISCOUNT_FOR_DISTINCT_BOOKS, actualPrice);

    }

    @Test
    @DisplayName("book pricing should return detailed pricing summary")
    void bookPricing_shouldReturnDetailedPricingSummary() {

        List<BookDto> listOfBooks = new ArrayList<>();

        BookDto firstBook = new BookDto(ONE, ONE);
        listOfBooks.add(firstBook);

        BookDto secondBook = new BookDto(TWO, TWO);
        listOfBooks.add(secondBook);

        BookDto thirdBook = new BookDto(THREE, THREE);
        listOfBooks.add(thirdBook);

        BookDto fourthBook = new BookDto(FOUR, TWO);
        listOfBooks.add(fourthBook);

        BookDto fifthBook = new BookDto(FIVE, ONE);
        listOfBooks.add(fifthBook);

        PricingSummaryDto pricingSummary = bookPricingService.bookPricing(listOfBooks);

        assertEquals(PRICE_OF_NINE_BOOKS_WITHOUT_DISCOUNT,pricingSummary.getActualPrice());
        assertEquals(PRICE_OF_NINE_BOOKS_WITH_DISCOUNT,pricingSummary.getTotalDiscount());
        assertEquals(PRICE_OF_NINE_BOOKS_AFTER_APPLY_DISCOUNT_FOR_DISTINCT_BOOKS, pricingSummary.getFinalPrice());
    }
}
