package com.daily.bill.domain.purchase;
import java.io.Serializable;
import java.util.List;

import com.daily.bill.domain.user.User;
/**
*@author Jin Rongquan
*@version Sun Apr 17 14:17:27 CST 2016
*/
public class Purchase implements Serializable, Comparable<Purchase> {
	private Integer id;
	private Double totalRealPay;
	private Long payAt;
	private List<User> userList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	@Override
	public int compareTo(Purchase o) {
		return this.getId().compareTo(o.getId());
	}
	
}