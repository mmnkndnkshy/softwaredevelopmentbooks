package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import com.mmnkndn.kata.softwaredevelopmentbooks.service.SoftwareDevelopmentBooksService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;



import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SoftwareDevelopmentBooksController.class)
public class SoftwareDevelopmentBooksControllerTest {

    @Autowired
    private SoftwareDevelopmentBooksController softwareDevelopmentBooksController;

    @MockBean
    private SoftwareDevelopmentBooksService softwareDevelopmentBooksService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("SoftwareDevelopmentBooks controller bean should not be null")
    void softwareDevelopmentBooksController_shouldNotBeNull(){
        assertThat(softwareDevelopmentBooksController).isNotNull();
    }

    @Test
    @DisplayName("API getBooks should return status ok")
    void getBooks_Api_shouldReturn_statusOk() throws Exception {
        mockMvc.perform(get("/api/softwaredevelopmentbooks/getbooks")).andExpect(status().isOk());
    }
}
