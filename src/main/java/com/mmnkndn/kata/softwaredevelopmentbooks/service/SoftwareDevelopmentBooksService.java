package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.Book;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SoftwareDevelopmentBooksService {

    @Autowired
    private ModelMapper softwareDevelopmentBookModelMapper;

    public List<Book> getBooks() {
        return Arrays.stream(SoftwareDevelopmentBook.values()).map(bookEnum -> softwareDevelopmentBookModelMapper.map(bookEnum, Book.class)).collect(Collectors.toList());
    }
}
