package com.daily.bill.domain.paybill;

import java.util.List;

import com.daily.bill.domain.user.User;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 10:27:15 AM
*/
public class IngredientPayWeekBillDetail {
	private Long pay_at;
	private Double realPay;
	private List<User> userList;
	private Double duePay;
	private Integer purchaseId;
	public Long getPay_at() {
		return pay_at;
	}
	public void setPay_at(Long pay_at) {
		this.pay_at = pay_at;
	}
	public Double getRealPay() {
		return realPay;
	}
	public void setRealPay(Double realPay) {
		this.realPay = realPay;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	public Double getDuePay() {
		return duePay;
	}
	public void setDuePay(Double duePay) {
		this.duePay = duePay;
	}
	public Integer getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(Integer purchaseId) {
		this.purchaseId = purchaseId;
	}
	
}
