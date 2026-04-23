package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class BookPricingEngine {

    private static final int ZERO = 0;

    private static final int ONE = 1;

    private final BookPricingCalculator bookPricingCalculator;

    private final BookCountReducer bookCountReducer;

    private final Map<String, Double> memo = new HashMap<>();

    public double getOptimalPrice(Map<Integer, Integer> bookMap) {

        List<Integer> counts = bookMap.values().stream()
                .filter(c -> c > ZERO)
                .sorted(Comparator.reverseOrder())
                .toList();

        return calculateMinPrice(counts);
    }

    private double calculateMinPrice(List<Integer> counts) {

        if (counts.isEmpty()) return ZERO;

        String key = counts.toString();
        if (memo.containsKey(key)) return memo.get(key);

        double minPrice = IntStream.rangeClosed(ONE, counts.size())
                .mapToDouble(size ->
                        bookPricingCalculator.calculateGroupPrice(size)
                                + calculateMinPrice(bookCountReducer.reduce(counts, size))
                )
                .min()
                .orElse(Double.MAX_VALUE);

        memo.put(key, minPrice);
        return minPrice;
    }
}
