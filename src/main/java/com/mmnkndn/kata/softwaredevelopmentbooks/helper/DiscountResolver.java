package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.DiscountProviderEnum;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

public final class DiscountResolver {

    private static final int ZERO_PERCENTAGE = 0;

    private DiscountResolver() {
    }

    public static Optional<DiscountProviderEnum> getDiscount(int distinctBooks) {
        return Arrays.stream(DiscountProviderEnum.values())
                .sorted(Comparator.reverseOrder())
                .filter(d -> d.getNumberOfDistinctBooks() <= distinctBooks)
                .findFirst();
    }

    public static int getDiscountPercentage(int distinctBooks) {
        return getDiscount(distinctBooks)
                .map(DiscountProviderEnum::getDiscountPercentage)
                .orElse(ZERO_PERCENTAGE);
    }
}
