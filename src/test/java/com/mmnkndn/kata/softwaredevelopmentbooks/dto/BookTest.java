package com.mmnkndn.kata.softwaredevelopmentbooks.dto;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BookTest {
    @Autowired
    private ModelMapper softwareDevelopmentBookModelMapper;

    @Test
    @DisplayName("SoftwareDevelopmentBook enum should map to Book DTO correctly")
    void softwareDevelopmentBookEnumToBook_shouldMapAllFieldssCorrectly() {

        SoftwareDevelopmentBook cleanCodeBookEnum = SoftwareDevelopmentBook.CLEAN_CODE;

        Book cleanCodeBook = softwareDevelopmentBookModelMapper.map(cleanCodeBookEnum, Book.class);

        assertEquals(cleanCodeBookEnum.getId(), cleanCodeBook.getId(), "Book Id mapping successfully");
        assertEquals(cleanCodeBookEnum.getTitle(), cleanCodeBook.getTitle(), "Book Title mapping successfully");
        assertEquals(cleanCodeBookEnum.getAuthor(), cleanCodeBook.getAuthor(), "Book Author mapping); successfully");
        assertEquals(cleanCodeBookEnum.getYear(), cleanCodeBook.getYear(), "Book Year mapping successfully");
        assertEquals(cleanCodeBookEnum.getPrice(), cleanCodeBook.getPrice(), "Book Price mapping successfully");

    }
}
