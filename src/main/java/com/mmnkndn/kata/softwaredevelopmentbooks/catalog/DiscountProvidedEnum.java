package com.mmnkndn.kata.softwaredevelopmentbooks.catalog;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscountProvidedEnum {

    TWO_DISTINCT_BOOKS(2, 5), THREE_DISTINCT_BOOKS(3, 10);

    private int numberOfDistinctBooks;
    private int discountPercentage;
}
