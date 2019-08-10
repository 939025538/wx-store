package org.forstudy.sell.utils;

public class MathUtil {

    private static final Double MONEY_RANG = 0.01;

    public static Boolean equal(Double d1 , Double d2){
        return Math.abs(d1 - d2) < MONEY_RANG ? true : false ;
    }
}
