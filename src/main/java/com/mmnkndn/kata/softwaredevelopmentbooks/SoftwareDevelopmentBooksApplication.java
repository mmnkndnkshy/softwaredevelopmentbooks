package com.mmnkndn.kata.softwaredevelopmentbooks;


import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SoftwareDevelopmentBooksApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoftwareDevelopmentBooksApplication.class, args);
    }

    @Bean
    public ModelMapper softwareDevelopmentBooksModelmapper() {
        return new ModelMapper();
    }
}
