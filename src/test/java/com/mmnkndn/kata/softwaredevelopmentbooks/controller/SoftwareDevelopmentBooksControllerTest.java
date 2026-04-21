package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@WebMvcTest
public class SoftwareDevelopmentBooksControllerTest {

    @Autowired
    private SoftwareDevelopmentBooksController softwareDevelopmentBooksController;

    @Test
    @DisplayName("SoftwareDevelopmentBooks controller bean should not be null")
    void softwareDevelopmentBooksController_shouldNotBeNull(){
        assertThat(softwareDevelopmentBooksController).isNotNull();
    }
}
