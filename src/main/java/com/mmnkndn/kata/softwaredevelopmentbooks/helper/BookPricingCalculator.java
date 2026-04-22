package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookGroup;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.PricingSummaryDto;

import java.util.List;

public final class BookPricingCalculator {

    private BookPricingCalculator() {
    }

    public static PricingSummaryDto calculate(List<BookGroup> groups) {

        double actualPrice =
                groups.stream()
                        .mapToDouble(BookGroup::getActualPrice)
                        .sum();

        double discount =
                groups.stream()
                        .mapToDouble(BookGroup::getDiscount)
                        .sum();

        PricingSummaryDto pricingSummary = new PricingSummaryDto();
        pricingSummary.setActualPrice(actualPrice);
        pricingSummary.setTotalDiscount(discount);
        pricingSummary.setFinalPrice(actualPrice - discount);

        return pricingSummary;
    }
}
