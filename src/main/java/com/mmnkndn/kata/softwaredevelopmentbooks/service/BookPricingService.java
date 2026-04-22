package com.mmnkndn.kata.softwaredevelopmentbooks.service;

import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.DiscountProviderEnum;
import com.mmnkndn.kata.softwaredevelopmentbooks.catalog.SoftwareDevelopmentBook;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookDto;
import com.mmnkndn.kata.softwaredevelopmentbooks.dto.BookGroup;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookPricingService {
    private static final int HUNDRED = 100;
    private static final int ZERO_PERCENTAGE = 0;
    private static final int ONE_QUANTITY = 1;


    public Double bookPricing(List<BookDto> listOfBooks) {


        Map<Integer, Integer> numberOfBooksMap = listOfBooks.stream().collect(Collectors.toMap(BookDto::getId, BookDto::getNoOfBooks));

        List<BookGroup> listOfBookGroups = getBookGroupsWithDiscount(numberOfBooksMap, new ArrayList<>());

        BookGroup bookGroupWithoutDiscount = getBookGroupWithoutDiscount(numberOfBooksMap);

        listOfBookGroups.add(bookGroupWithoutDiscount);

        double actualPrice = listOfBookGroups.stream().mapToDouble(BookGroup::getActualPrice).sum();

        double discount = listOfBookGroups.stream().mapToDouble(BookGroup::getDiscount).sum();

        return actualPrice - discount;

    }

    private List<BookGroup> getBookGroupsWithDiscount(Map<Integer, Integer> numberofBooksMap, List<BookGroup> bookGroup) {

        Optional<DiscountProviderEnum> discount = getDiscount(numberofBooksMap.size());

        if (discount.isPresent()) {

            int bookGroupSize = discount.get().getNumberOfDistinctBooks();

            List<Integer> listOfDistinctBooks = numberofBooksMap.keySet().stream().limit(bookGroupSize).collect(Collectors.toList());

            BookGroup currentBookGroup = getBookGroup(listOfDistinctBooks);

            bookGroup.add(currentBookGroup);

            cleanupDiscountedBooks(numberofBooksMap, listOfDistinctBooks);

            getBookGroupsWithDiscount(numberofBooksMap, bookGroup);

        }

        return bookGroup;

    }

    private BookGroup getBookGroupWithoutDiscount(Map<Integer, Integer> numberofBooksMap) {

        Map<Integer, Double> bookIdPricMap = getBookIdPriceMap();

        Set<Integer> bookIds = numberofBooksMap.keySet();

        double actualPrice = bookIds.stream().mapToDouble(bookId -> bookIdPricMap.get(bookId) * numberofBooksMap.get(bookId)).sum();

        return new BookGroup(new ArrayList<>(bookIds), ZERO_PERCENTAGE, actualPrice, BigDecimal.ZERO.doubleValue());

    }

    private Optional<DiscountProviderEnum> getDiscount(int numberOfDistinctBooks) {

        return Arrays.stream(DiscountProviderEnum.values()).sorted(Comparator.reverseOrder()).filter(discountProvidedEnum -> discountProvidedEnum.getNumberOfDistinctBooks() <= numberOfDistinctBooks).findFirst();

    }

    private BookGroup getBookGroup(List<Integer> listOfBooksToGroup) {

        Map<Integer, Double> bookIdPriceMap = getBookIdPriceMap();

        double actualPrice = listOfBooksToGroup.stream().mapToDouble(bookId -> bookIdPriceMap.get(bookId) * ONE_QUANTITY).sum();

        int discountPercentage = getDiscountPercentage(listOfBooksToGroup.size());

        double discountedPrice = (actualPrice * discountPercentage) / HUNDRED;

        return new BookGroup(listOfBooksToGroup, discountPercentage, actualPrice, discountedPrice);

    }

    private Map<Integer, Double> getBookIdPriceMap() {

        return Arrays.stream(SoftwareDevelopmentBook.values()).collect(Collectors.toMap(SoftwareDevelopmentBook::getId, SoftwareDevelopmentBook::getPrice));

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

        Optional<DiscountProviderEnum> discount = Arrays.stream(DiscountProviderEnum.values()).sorted(Comparator.reverseOrder()).filter(discountProvidedEnum -> discountProvidedEnum.getNumberOfDistinctBooks() <= distinctBooks).findFirst();

        return discount.map(DiscountProviderEnum::getDiscountPercentage).orElse(ZERO_PERCENTAGE);
    }
}
