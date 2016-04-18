package com.daily.bill.common.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.FastDateFormat;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 2:56:30 PM
*/
public class DateUtils {
	
	private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	private final static FastDateFormat defaultTimeFormat = FastDateFormat.getInstance(DEFAULT_DATE_FORMAT);
	
	/**
	 * @param date
	 * @param field
	 * @param amount
	 * @return 对指定的日期做加减运算；<br>
	 *         减：add(new Date(), Calendar.DATE, -1)返回昨天的日期<br>
	 *         加：add(new Date(), Calendar.YEAR, 1)返回一年后的今天
	 */
	public static Date add(Date date, int field, int amount){
		if(date == null){
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
    public static Date getDateByFillFields(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second){
    	Calendar cal = Calendar.getInstance();
    	cal.set(Calendar.YEAR, year);
    	cal.set(Calendar.MONTH, month);
    	cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    	cal.set(Calendar.HOUR_OF_DAY, hourOfDay);
    	cal.set(Calendar.MINUTE, minute);
    	cal.set(Calendar.SECOND, second);
    	cal.set(Calendar.MILLISECOND, 0);
    	return cal.getTime();
    }
    
    public static Date longToDate(Long dateTime){
    	if(dateTime == null){
    		return null;
    	}
    	return new Date(dateTime);
    }
    
    /**
     * 日期转成 yyyy-MM-dd HH:mm:ss 格式的字符串
     * @param date
     * @return
     */
    public static String formatToTimeStr(Date date) {
        if (date != null) {
            return defaultTimeFormat.format(date);
        }
        return null;
    }
    
    /**
     * 获取当前周的第一天
     * @return
     */
    public static Date getStartOfCurrentWeek(){
    	Calendar calendar = Calendar.getInstance();
    	int curDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    	if(curDayOfWeek > 1){
    		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	}
    	return getStartOfTheDate(calendar.getTime());
    }
    
    public static Date getStartOfWeekByDate(Date date){
    	if(date == null){
    		return null;
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
    	if(dayOfWeek > 1){
    		calendar.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
    	}
    	return getStartOfTheDate(calendar.getTime());
    }
    
    public static Date getStartOfTheDate(Date date){
    	if(date == null){
    		return null;
    	}
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(date);
    	calendar.set(Calendar.HOUR_OF_DAY, 0);
    	calendar.set(Calendar.MINUTE, 0);
    	calendar.set(Calendar.SECOND, 0);
    	calendar.set(Calendar.MILLISECOND, 0);
    	return calendar.getTime();
    }
    
    public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		System.out.println(calendar.get(Calendar.DAY_OF_WEEK));
    	Date date = getDateByFillFields(2016, 3, 16, 0, 0, 0);
    	System.out.println(getStartOfWeekByDate(date));
	}
}
