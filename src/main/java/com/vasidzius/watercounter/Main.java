package com.vasidzius.watercounter;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        WaterCounter waterCounter = new WaterCounter();
        int[] landscape = Arrays.stream(args).mapToInt(Integer::parseInt).toArray();
        Validator validator = new Validator();
        validator.validate(landscape);
        System.out.println("Water amount is : " + waterCounter.calculateWaterAmount(landscape));
    }
}
