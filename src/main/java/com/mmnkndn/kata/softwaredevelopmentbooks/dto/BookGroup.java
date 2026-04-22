package com.mmnkndn.kata.softwaredevelopmentbooks.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BookGroup {

    private List<Integer> listOfBooks;

    private int discountPercentage;

    private double actualPrice;

    private double discount;

}
