package com.mmnkndn.kata.softwaredevelopmentbooks.catalog;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SoftwareDevelopmentBookTest {

    private static final int NUMBER_OF_BOOKS = 5;

    @Test
    @DisplayName("SoftwareDevelopmentBook should contain five books")
    void softwaredevelopmentBooksEnum_shouldContainFiveDevelopmentBooks() {
        assertEquals(NUMBER_OF_BOOKS, SoftwareDevelopmentBook.values().length);
    }
}