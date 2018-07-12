package com.vasidzius.watercounter;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ValidatorTest {

    private Validator validator = new Validator();

    @Test(expected = IllegalArgumentException.class)
    public void lengthValidation(){
        validator.validate(new int[32001]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeValueValidation(){
        int[] landscape = new int[1];
        landscape[0] = -1;
        validator.validate(landscape);
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightValueValidation(){
        int[] landscape = new int[1];
        landscape[0] = 32001;
        validator.validate(landscape);
    }

    @Parameters
    @Test(expected = IllegalArgumentException.class)
    public void heightValueValidation2(int[] landscape){
        validator.validate(landscape);
    }

    private Object[] parametersForHeightValueValidation2() {
        return new Object[]{
                new int[]{-1, -1, -1},
                new int[]{60000, 60000, 60000},
        };
    }
}