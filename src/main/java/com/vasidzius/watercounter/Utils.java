package com.vasidzius.watercounter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Utils {

    static List<Integer> toList(int[] landscape) {
        return Arrays.stream(landscape).boxed().collect(Collectors.toList());
    }

    static List<Integer> getIntArrayReverse(Integer from, Integer to) {
        return IntStream.range(to + 1, from + 1).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }
}
