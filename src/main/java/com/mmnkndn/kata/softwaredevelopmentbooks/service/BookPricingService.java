package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.DiscountProvidedEnum;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookPricingService {
    private static final int HUNDRED = 100;
    private static final int ZERO_PERCENTAGE = 0;
    private static final int ONE_QUANTITY = 1;


    public Double bookPricing(List<BookDto> listOfBooks) {

        Map<Integer, Double> bookIdPriceMap = Arrays.stream(SoftwareDevelopmentBook.values()).collect(Collectors.toMap(SoftwareDevelopmentBook::getId, SoftwareDevelopmentBook::getPrice));

        Map<Integer, Integer> numberOfBooksMap = listOfBooks.stream().collect(Collectors.toMap(BookDto::getId, BookDto::getNoOfBooks));

        int discountGroup = numberOfBooksMap.size();

        List<Integer> groupItems = numberOfBooksMap.keySet().stream().limit(discountGroup).collect(Collectors.toList());

        double actualPriceForDiscountedItem = groupItems.stream().mapToDouble(bookId -> bookIdPriceMap.get(bookId) * ONE_QUANTITY).sum();

        double discountedPrice = actualPriceForDiscountedItem * getDiscountPercentage(discountGroup) / HUNDRED;

        cleanupDiscountedBooks(numberOfBooksMap, groupItems);

        double priceForDiscountedBooks = actualPriceForDiscountedItem - discountedPrice;

        Set<Integer> remainingBooks = numberOfBooksMap.keySet();

        double priceForRemainingBooks = remainingBooks.stream().mapToDouble(bookId -> bookIdPriceMap.get(bookId)).sum();

        return (priceForDiscountedBooks + priceForRemainingBooks);

    }

    private void cleanupDiscountedBooks(Map<Integer, Integer> numberOfBooksMap, List<Integer> groupItems) {
        groupItems.forEach(bookId -> {
            int numberOfBooks = numberOfBooksMap.get(bookId);
            if (numberOfBooks > ONE_QUANTITY) {
                numberOfBooksMap.put(bookId, numberOfBooks - ONE_QUANTITY);
            } else {
                numberOfBooksMap.remove(bookId);
            }
        });
    }

    private int getDiscountPercentage(long distinctBooks) {

        Optional<DiscountProvidedEnum> discount = Arrays.stream(DiscountProvidedEnum.values()).sorted(Comparator.reverseOrder()).filter(discountProvidedEnum -> discountProvidedEnum.getNumberOfDistinctBooks() <= distinctBooks).findFirst();

        return (discount.isPresent()) ? discount.get().getDiscountPercentage() : ZERO_PERCENTAGE;
    }
}
