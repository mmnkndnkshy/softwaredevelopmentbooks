package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SoftwareDevelopmentBooksServiceTest {

    private static final int NUMBER_OF_BOOKS = 5;

    @Autowired
    private SoftwareDevelopmentBooksService softwareDevelopmentBooksService;

    @Test
    @DisplayName("Get books should return five software development books")
    void getBooks_shouldReturnListOfFiveBooks(){
        List<Book> books = softwareDevelopmentBooksService.getBooks();
        assertEquals(NUMBER_OF_BOOKS,books.size(),"Got all softwate development books");
    }
}
