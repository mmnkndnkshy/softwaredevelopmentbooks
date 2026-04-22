package com.mmnkndn.kata.softwaredevelopmentbooks;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SoftwareDevelopmentApplicationTest {

    @Autowired
    private ModelMapper softwareDevelopmentAppModelMapper;

    @Test
    @DisplayName("Software Development books application context loads successfully")
    void softwaredevelopmentbooks_applicationcontext_shouldnotbenull(ApplicationContext context) {
        assertNotNull(context, "Software Development books application context loaded successfully");
    }

    @Test
    @DisplayName("Software Development books application model mapper should not be null")
    void softwareDevelopmentAppModelMapper_shouldnotbenull() {
        assertThat(softwareDevelopmentAppModelMapper);
    }
}
