package com.example.demo.utils;

public class Util {

    public static float rateConversion(String moneyType) {
        if (moneyType == "GBP") {
            return 12.5F;
        } else if (moneyType == "EUR") {
            return 30.6F;
        } else if (moneyType == "CHF") {
            return 15.8F;
        }
        return 1;
    }

}
