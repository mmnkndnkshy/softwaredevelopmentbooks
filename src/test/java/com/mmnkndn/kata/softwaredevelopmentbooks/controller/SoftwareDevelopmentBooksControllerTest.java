package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import com.mmnkndn.kata.softwaredevelopmentbooks.service.SoftwareDevelopmentBooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SoftwareDevelopmentBooksController.class)
public class SoftwareDevelopmentBooksControllerTest {

    @Value("${softwaredevelopmentbooks.controller.path}${softwaredevelopmentbooks.endpoints.getbooks}")
    private String GETBOOKS_ENPOINT;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SoftwareDevelopmentBooksController softwareDevelopmentBooksController;

    @Autowired
    private SoftwareDevelopmentBooksService softwareDevelopmentBooksService;

    @BeforeEach
    void setup() {
        Mockito.reset(softwareDevelopmentBooksService);
    }

    @Test
    @DisplayName("SoftwareDevelopmentBooks controller bean should not be null")
    void softwareDevelopmentBooksController_shouldNotBeNull() {
        assertThat(softwareDevelopmentBooksController).isNotNull();
    }

    @Test
    @DisplayName("API getBooks should return status ok")
    void getBooks_Api_shouldReturn_statusOk() throws Exception {
        mockMvc.perform(get(GETBOOKS_ENPOINT)).andExpect(status().isOk());
    }

    @TestConfiguration
    static class SoftwareDevelopmentBooksControllerTestContextConfiguration {
        @Bean
        public SoftwareDevelopmentBooksService softwareDevelopmentBooksService() {
            return Mockito.mock(SoftwareDevelopmentBooksService.class);
        }
    }
}
