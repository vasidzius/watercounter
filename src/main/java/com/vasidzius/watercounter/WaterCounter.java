package com.vasidzius.watercounter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.vasidzius.watercounter.Utils.getIntArrayReverse;
import static com.vasidzius.watercounter.Utils.toList;

public class WaterCounter {

    public long calculateWaterAmount(int[] landscape) {
        List<Integer> list = toList(landscape);
        Optional<Integer> minHeight = list.stream().min(Integer::compareTo);
        Optional<Integer> maxHeight = list.stream().max(Integer::compareTo);
        long amount = 0;
        if (maxHeight.isPresent() && !maxHeight.get().equals(minHeight.get())) {
            amount = calculateAmount(list, maxHeight.get(), amount);
        }
        return amount;
    }

    private long calculateAmount(List<Integer> list, Integer maxHeight, long amount) {
        ListIterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()) {
            int currentIndex = iterator.nextIndex();
            Integer currentValue = iterator.next();
            List<Integer> column = getIntArrayReverse(maxHeight, currentValue);
            for (Integer columnValue : column) {
                if (isBounded(currentIndex, list, columnValue)) {
                    amount += columnValue - currentValue;
                    break;
                }
            }
        }
        return amount;
    }

    private boolean isBounded(int currentIndex, List<Integer> list, int columnValue) {
        return isBoundedFromLeft(currentIndex, list, columnValue) && isBoundedFromRight(currentIndex, list, columnValue);
    }

    private boolean isBoundedFromRight(int currentIndex, List<Integer> list, int columnValue) {
        List<Integer> rightPart = list.subList(currentIndex, list.size());
        return isBoundExist(columnValue, rightPart);
    }

    private boolean isBoundedFromLeft(int currentIndex, List<Integer> list, int columnValue) {
        List<Integer> leftPart = list.subList(0, currentIndex).stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        return isBoundExist(columnValue, leftPart);
    }

    private boolean isBoundExist(int columnValue, List<Integer> listPart) {
        for (Integer next : listPart) {
            if (next >= columnValue) {
                return true;
            }
        }
        return false;
    }
}
