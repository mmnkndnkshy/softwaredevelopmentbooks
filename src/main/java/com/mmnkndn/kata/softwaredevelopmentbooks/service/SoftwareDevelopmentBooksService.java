package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.Book;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoftwareDevelopmentBooksService {

    public List<Book> getBooks() {
        return Arrays.stream(SoftwareDevelopmentBook.values()).map(bookEnum -> new Book(bookEnum.getId(), bookEnum.getTitle(), bookEnum.getAuthor(), bookEnum.getYear(), bookEnum.getPrice())).collect(Collectors.toList());
    }
}
