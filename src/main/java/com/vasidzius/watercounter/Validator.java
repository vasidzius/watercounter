package com.vasidzius.watercounter;

import java.util.List;
import java.util.Optional;

import static com.vasidzius.watercounter.Utils.toList;

class Validator {
    void validate(int[] landscape) {
        landscapeLengthValidation(landscape);
        validateRange(landscape);
    }

    private void validateRange(int[] landscape) {
        List<Integer> list = toList(landscape);
        Optional<Integer> minHeight = list.stream().min(Integer::compareTo);
        Optional<Integer> maxHeight = list.stream().max(Integer::compareTo);
        minHeight.ifPresent(min -> rangeValidation(min, maxHeight.get()));
    }

    private void rangeValidation(Integer minHeight, Integer maxHeight) {
        if (minHeight < 0) {
            throw new IllegalArgumentException("Negative values aren't allowed");
        }
        if (maxHeight > 32000) {
            throw new IllegalArgumentException("Max value can't be more than 32000");
        }
    }

    private void landscapeLengthValidation(int[] landscape) {
        if(landscape.length > 32000){
            throw new IllegalArgumentException("Landscape length can't be more than 32000");
        }
    }
}
