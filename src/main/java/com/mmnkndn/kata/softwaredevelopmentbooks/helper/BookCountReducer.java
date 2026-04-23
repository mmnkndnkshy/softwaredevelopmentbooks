package com.mmnkndn.kata.softwaredevelopmentbooks.helper;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class BookCountReducer {

    public List<Integer> reduce(List<Integer> counts, int size) {

        return IntStream.range(0, counts.size())
                .mapToObj(i -> i < size ? counts.get(i) - 1 : counts.get(i))
                .filter(c -> c > 0)
                .sorted(Comparator.reverseOrder())
                .toList();
    }
}
