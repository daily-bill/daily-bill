package com.daily.bill.common.enums;
/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 3:33:29 PM
*/
public enum LogicType {
	
	TRUE(1),
	FALSE(0);
	
	private final int value;
	
	LogicType(int value){
		this.value = value;
	}
	
	public int getValue() {
        return value;
    }

    public static boolean isTrue(Integer value) {
        if (value == null) {
            return false;
        } else if (TRUE.getValue() == value) {
            return true;
        }
        return false;
    }

    public static Integer getValue(Boolean isSettled) {
        if (isSettled != null) {
            return isSettled ? TRUE.getValue() : FALSE.getValue();
        }
        return null;
    }
}
