package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.DiscountProviderEnum;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

@Component
public class DiscountResolver {

    private static final int ZERO_PERCENTAGE = 0;

    public Optional<DiscountProviderEnum> getDiscount(int distinctBooks) {
        return Arrays.stream(DiscountProviderEnum.values())
                .sorted(Comparator.reverseOrder())
                .filter(d -> d.getNumberOfDistinctBooks() <= distinctBooks)
                .findFirst();
    }

    public int getDiscountPercentage(int distinctBooks) {
        return getDiscount(distinctBooks)
                .map(DiscountProviderEnum::getDiscountPercentage)
                .orElse(ZERO_PERCENTAGE);
    }
}
