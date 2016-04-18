package com.daily.bill.common.enums;
/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:27:07 PM
*/
public enum IngredientPayBillDetailStatus {
	
	CREATED(0, "已创建"),
	PAIED(1, "已缴款");
	
	IngredientPayBillDetailStatus(Integer value, String name){
		this.value = value;
		this.name = name;
	}
	
	private final int value;
	
	private final String name;

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public boolean equalsValue(Integer value){
		if(value == null){
			return false;
		}
		if(this.value == value){
			return true;
		}
		return false;
	}
	
	public static String getName(Integer value){
		if(value == null){
			return null;
		}
		for(IngredientPayBillDetailStatus status : IngredientPayBillDetailStatus.values()){
			if(status.equalsValue(value)){
				return status.getName();
			}
		}
		return null;
	}
}
