package com.daily.bill.domain.paybill;
/**
*@Author Jin Rongquan
*@Version Apr 20, 2016 8:15:37 AM
*/
public class UserDuePayBill {
	private Integer userId;
	private String userName;
	private Double duePay;

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
	public Double getDuePay() {
		return duePay;
	}
	public void setDuePay(Double duePay) {
		this.duePay = duePay;
	}
}
