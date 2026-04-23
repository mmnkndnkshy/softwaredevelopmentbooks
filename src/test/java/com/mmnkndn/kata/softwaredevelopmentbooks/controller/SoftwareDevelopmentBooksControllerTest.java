package com.mmnkndn.kata.softwaredevelopmentbooks.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mmnkndn.kata.softwaredevelopmentbooks.api.model.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.service.BookPricingService;
import com.mmnkndn.kata.softwaredevelopmentbooks.service.SoftwareDevelopmentBooksService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SoftwareDevelopmentBooksController.class)
@Import(SoftwareDevelopmentBooksControllerTest.TestConfig.class)
@TestPropertySource(properties = {
        "softwaredevelopmentbooks.controller.path=/api/softwaredevelopmentbooks",
        "softwaredevelopmentbooks.endpoints.getbooks=/getBooks",
        "softwaredevelopmentbooks.endpoints.getpricingsummary=/getPricingSummary"
})
class SoftwareDevelopmentBooksControllerTest {

    @TestConfiguration
    static class TestConfig {

        @Bean
        SoftwareDevelopmentBooksService softwareDevelopmentBooksService() {
            return Mockito.mock(SoftwareDevelopmentBooksService.class);
        }

        @Bean
        BookPricingService bookPricingService() {
            return Mockito.mock(BookPricingService.class);
        }
    }

    private static final String GET_BOOKS_ENDPOINT = "/api/softwaredevelopmentbooks/getBooks";
    private static final String PRICE_SUMMARY_ENDPOINT = "/api/softwaredevelopmentbooks/getPricingSummary";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SoftwareDevelopmentBooksController controller;

    @Autowired
    private SoftwareDevelopmentBooksService softwareDevelopmentBooksService;

    @Autowired
    private BookPricingService bookPricingService;

    @BeforeEach
    void setup() {
        Mockito.reset(softwareDevelopmentBooksService, bookPricingService);
    }

    @Test
    @DisplayName("SoftwareDevelopmentBooks controller bean should not be null")
    void softwareDevelopmentBooksController_shouldNotBeNull() {
        assertThat(controller).isNotNull();
    }

    @Test
    @DisplayName("API getBooks should return status ok")
    void getBooks_Api_shouldReturn_statusOk() throws Exception {
        mockMvc.perform(get(GET_BOOKS_ENDPOINT))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("API getPricingSummary should return status ok")
    void getPricingSummary_Api_shouldReturn_statusOk() throws Exception {
        List<BookDto> books = List.of(
                new BookDto(1, 1),
                new BookDto(2, 1),
                new BookDto(3, 1)
        );

        mockMvc.perform(post(PRICE_SUMMARY_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(books)))
                .andExpect(status().isOk());
    }
}