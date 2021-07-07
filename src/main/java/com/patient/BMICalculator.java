package com.patient;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BMICalculator
{

    static double calculateBmi(int inches, double pounds)
    {
        double bmi = (pounds * 703) / (inches * inches);
        return new BigDecimal(bmi)
            .setScale(2, RoundingMode.HALF_UP)
            .doubleValue();
    }
}
