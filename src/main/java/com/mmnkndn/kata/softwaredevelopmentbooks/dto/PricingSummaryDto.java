package com.mmnkndn.kata.softwaredevelopmentbooks.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PricingSummaryDto {

    private List<BookGroup> listOfBookGroups;

    private double actualPrice;

    private double totalDiscount;

    private double finalPrice;

}
