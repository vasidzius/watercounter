package com.vasidzius.watercounter;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WaterCounter {

    public static void main(String[] args) {
        WaterCounter waterCounter = new WaterCounter();
        int[] landscape = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        System.out.println("Water amount is : " + waterCounter.calculateWaterAmount(landscape));
    }

    public long calculateWaterAmount(int[] landscape) {
        landscapeLengthValidation(landscape);
        List<Integer> list = toList(landscape);
        Optional<Integer> minHeight = list.stream().min(Integer::compareTo);
        Optional<Integer> maxHeight = list.stream().max(Integer::compareTo);
        long amount = 0;
        if (maxHeight.isPresent() && !maxHeight.get().equals(minHeight.get())) {
            rangeValidation(minHeight.get(), maxHeight.get());
            ListIterator<Integer> iterator = list.listIterator();
            while (iterator.hasNext()) {
                int xIndex = iterator.nextIndex();
                Integer currentValue = iterator.next();
                List<Integer> column = getIntArrayReverse(maxHeight.get(), currentValue);
                for (Integer y : column) {
                    if (isBounded(xIndex, list, y)) {
                        amount += y - currentValue;
                        break;
                    }
                }
            }
        }
        return amount;
    }

    private void rangeValidation(Integer minHeight, Integer maxHeight) {
        if(minHeight < 0){
            throw new IllegalArgumentException("Negative values aren't allowed");
        }
        if(maxHeight > 32000){
            throw new IllegalArgumentException("Max value can't be more than 32000");
        }
    }

    private void landscapeLengthValidation(int[] landscape) {
        if(landscape.length > 32000){
            throw new IllegalArgumentException("Landscape length can't be more than 32000");
        }
    }

    private boolean isBounded(int xIndex, List<Integer> list, int y) {
        return isLeftBounded(xIndex, list, y) && isRightBounded(xIndex, list, y);
    }

    private boolean isRightBounded(int xIndex, List<Integer> list, int y) {
        List<Integer> rightPart = list.subList(xIndex, list.size());
        return isBoundExist(y, rightPart);
    }

    private boolean isLeftBounded(int xIndex, List<Integer> list, int y) {
        List<Integer> leftPart = list.subList(0, xIndex).stream().sorted(Collections.reverseOrder()).collect(Collectors.toList());
        return isBoundExist(y, leftPart);
    }

    private boolean isBoundExist(int y, List<Integer> leftPart) {
        for (Integer next : leftPart) {
            if (next >= y) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> toList(int[] landscape) {
        return Arrays.stream(landscape).boxed().collect(Collectors.toList());
    }

    private List<Integer> getIntArrayReverse(Integer from, Integer to) {
        return IntStream.range(to + 1, from + 1).boxed().sorted(Collections.reverseOrder()).collect(Collectors.toList());
    }
}
