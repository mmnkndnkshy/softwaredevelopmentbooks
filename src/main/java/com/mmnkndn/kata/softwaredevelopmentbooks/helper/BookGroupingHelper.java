package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class BookGroupingHelper {

    private static final int ONE_QUANTITY = 1;
    private static final int ZERO_PERCENTAGE = 0;

    private BookGroupingHelper() {
    }


    public static List<BookGroup> getGroupedBooks(Map<Integer, Integer> bookMap, List<BookGroup> groups) {

        return DiscountResolver.getDiscount(bookMap.size())
                .map(discount -> {

                    List<Integer> selectedBooks = bookMap.keySet().stream()
                            .limit(discount.getNumberOfDistinctBooks()).toList();

                    groups.add(createGroup(selectedBooks));

                    cleanup(bookMap, selectedBooks);

                    return getGroupedBooks(bookMap, groups); // recursion
                }).orElse(groups);
    }

    public static BookGroup getRemainingBooksGroup(Map<Integer, Integer> bookMap) {

        double actualPrice = bookMap.entrySet().stream()
                .mapToDouble(e -> BookCatalog.getPrice(e.getKey()) * e.getValue())
                .sum();

        return new BookGroup(new ArrayList<>(bookMap.keySet()), ZERO_PERCENTAGE, actualPrice, 0.0);
    }

    private static BookGroup createGroup(List<Integer> bookIds) {

        double actualPrice = bookIds.stream().mapToDouble(BookCatalog::getPrice).sum();

        int discount = DiscountResolver.getDiscountPercentage(bookIds.size());

        double discountAmount = (actualPrice * discount) / 100;

        return new BookGroup(bookIds, discount, actualPrice, discountAmount);
    }

    private static void cleanup(Map<Integer, Integer> map, List<Integer> usedBooks) {

        usedBooks.forEach(bookId -> {
            int count = map.get(bookId);
            if (count > ONE_QUANTITY) {
                map.put(bookId, count - ONE_QUANTITY);
            } else {
                map.remove(bookId);
            }
        });
    }
}
