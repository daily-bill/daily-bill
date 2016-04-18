package com.daily.bill.common.util;
/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 2:51:55 PM
*/
public class MoneyCalUtils {

	public static Double nullToZero(Double v) {
        return v == null ? 0 : v;
    }

    public static long yuanToFen(Double v) {
        return Math.round(nullToZero(v) * 100);
    }

    public static Double add(Double v1, Double v2) {
        long value1 = yuanToFen(v1);
        long value2 = yuanToFen(v2);
        return (value1 + value2) / 100D;
    }


    public static Double sub(Double v1, Double v2) {
        long value1 = yuanToFen(v1);
        long value2 = yuanToFen(v2);
        return (value1 - value2) / 100D;
    }
}
