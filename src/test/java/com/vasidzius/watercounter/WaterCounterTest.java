package com.vasidzius.watercounter;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(JUnitParamsRunner.class)
public class WaterCounterTest {

    private WaterCounter waterCounter = new WaterCounter();

    @Test
    @Parameters
    public void count(int[] landscape, long expectedAmount) {
        //when
        long amount = waterCounter.calculateWaterAmount(landscape);

        //then
        assertEquals(expectedAmount, amount);
    }

    private Object[] parametersForCount() {
        return new Object[]{
                new Object[]{new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}, 9},
                new Object[]{new int[]{5}, 0},
                new Object[]{new int[]{5, 5}, 0},
                new Object[]{new int[]{5, 5, 5}, 0},
                new Object[]{new int[]{5, 5, 5, 5, 5, 5}, 0},
                new Object[]{new int[]{4, 5, 5, 5, 5, 4}, 0},
                new Object[]{new int[]{5, 5, 5, 4, 5, 5}, 1},
                new Object[]{new int[]{5, 2, 3, 4, 5, 4, 0, 3, 1}, 9},
                new Object[]{new int[]{4, 5, 5, 4, 5, 4}, 1},
                new Object[]{new int[]{5, 4, 3, 2, 1, 0}, 0},
                new Object[]{new int[]{5, 4, 4, 4, 4, 5}, 4},
                new Object[]{new int[]{5, 0, 4, 0, 3, 0}, 7},
                new Object[]{new int[]{5, 3, 3, 3, 3, 5}, 8},
                new Object[]{new int[]{5, 3, 3, 2, 3, 4}, 5},
                new Object[]{new int[]{1, 2, 3, 4, 4, 5, 6, 7}, 0},
                new Object[]{new int[]{1, 2, 3, 4, 3, 4, 5, 6, 7}, 1},
                new Object[]{new int[]{1, 2, 3, 4, 4, 5, 6, 7, 0}, 0},
                new Object[]{new int[]{5, 0, 1, 0, 4, 3, 6, 4, 10, 5, 4, 3}, 19},
        };
    }

}