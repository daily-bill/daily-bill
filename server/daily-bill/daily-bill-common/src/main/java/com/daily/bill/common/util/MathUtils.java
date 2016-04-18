package com.daily.bill.common.util;

import java.math.BigDecimal;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 9:05:41 PM
*/
public class MathUtils {
	
	public static Double div(Double v1, Double v2){
		return div(v1, v2, 2);
	}
	
	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
	 * @param v1 被除数
	 * @param v2 除数
	 * @return
	 */
	public static Double div(Double v1, Double v2, int scale){
		v1 = nullToZero(v1);
		if(v2 == null || v2 == 0){
			throw new RuntimeException("divisor must not be null or zero");
		}
		if(scale < 0){
			throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
		}
		BigDecimal value1 = new BigDecimal(nullToZero(v1).toString());
		BigDecimal value2 = new BigDecimal(nullToZero(v2).toString());
		return value1.divide(value2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	private static Double nullToZero(Double v){
		return v == null ? 0D : v;
	}
	
}
