package com.mmnkndn.kata.softwaredevelopmentbooks;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SoftwareDevelopmentApplicationTest {

    @Test
    @DisplayName("Software Development books application context loads successfully")
    void softwaredevelopmentbooks_applicationcontext_shouldnotbenull(ApplicationContext context) {
        assertNotNull(context, "Software Development books application context loaded successfully");
    }
}
