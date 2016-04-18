package com.daily.bill.domain.paybill;
/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 8:51:14 AM
*/
public class IngredientPayBillDetailAmount {
	private Integer userId;
	private String userName;
	private Double totalDuePay;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getTotalDuePay() {
		return totalDuePay;
	}
	public void setTotalDuePay(Double totalDuePay) {
		this.totalDuePay = totalDuePay;
	}
	
}
