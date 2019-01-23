package com.vasidzius.watercounter;

import com.sun.org.apache.regexp.internal.RE;

import java.util.*;
import java.util.stream.Collectors;

import static com.vasidzius.watercounter.Utils.getIntArrayReverse;

public class WaterCounter {

    public int calculateWaterAmount(int[] landscape) {
        int i = 0;
        int j = landscape.length - 1;

        int left = landscape[i];
        int right = landscape[j];

        Result result = new Result();

        for(; i < j; ){
            if(left < right){
                left = calculateHole(result, ++i, left, landscape);
            } else {
                right = calculateHole(result, --j, right, landscape);
            }
        }

        return result.getResult();
    }

    private int calculateHole(Result result, int nextIndex, int treeHeight, int[] landscape) {
        int nextTreeHeight = landscape[nextIndex];
        if(treeHeight > nextTreeHeight){
            result.setResult(result.getResult() + treeHeight - nextTreeHeight);
        } else {
            treeHeight = nextTreeHeight;
        }
        return treeHeight;
    }


}
