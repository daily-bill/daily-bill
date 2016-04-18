package com.daily.bill.domain.purchase.operate;

import java.util.List;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:08:20 PM
*/
public class PurchaseApplyParam {
	private Double totalRealPay;
	private Long payAt;
	private List<Integer> userIds;
	
	public Double getTotalRealPay() {
		return totalRealPay;
	}
	public void setTotalRealPay(Double totalRealPay) {
		this.totalRealPay = totalRealPay;
	}
	public Long getPayAt() {
		return payAt;
	}
	public void setPayAt(Long payAt) {
		this.payAt = payAt;
	}
	public List<Integer> getUserIds() {
		return userIds;
	}
	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}
	
}
