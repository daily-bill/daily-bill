package com.daily.bill.domain.paybill;
/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 8:51:14 AM
*/
public class IngredientPayBillDetailAmount {
	private Integer userId;
	private String userName;
	private Double totalDuePay;
	private Long createStartDate;
	private Long createEndDate;
	private Integer status;
	
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
	public Long getCreateStartDate() {
		return createStartDate;
	}
	public void setCreateStartDate(Long createStartDate) {
		this.createStartDate = createStartDate;
	}
	public Long getCreateEndDate() {
		return createEndDate;
	}
	public void setCreateEndDate(Long createEndDate) {
		this.createEndDate = createEndDate;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "IngredientPayBillDetailAmount [userId=" + userId + ", userName=" + userName + ", totalDuePay="
				+ totalDuePay + ", createStartDate=" + createStartDate + ", createEndDate=" + createEndDate + "]";
	}

	
}
