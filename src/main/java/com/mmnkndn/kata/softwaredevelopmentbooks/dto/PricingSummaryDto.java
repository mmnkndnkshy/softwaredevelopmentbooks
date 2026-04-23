package com.mmnkndn.kata.softwaredevelopmentbooks.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PricingSummaryDto {

    //private List<BookGroup> listOfBookGroups;

    private final double actualPrice;

    private final double totalDiscount;

    private final double finalPrice;

    public static PricingSummaryDto of(double actualPrice,
                                       double totalDiscount,
                                       double finalPrice) {
        return new PricingSummaryDto(actualPrice, totalDiscount, finalPrice);
    }

}
