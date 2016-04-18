package com.daily.bill.domain.purchase;
/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 7:03:25 AM
*/
public class PurchaseWeekAmount {
	private Long payStartDate;
	private Long payEndDate;
	private Double totalPay;
	public Long getPayStartDate() {
		return payStartDate;
	}
	public void setPayStartDate(Long payStartDate) {
		this.payStartDate = payStartDate;
	}
	public Long getPayEndDate() {
		return payEndDate;
	}
	public void setPayEndDate(Long payEndDate) {
		this.payEndDate = payEndDate;
	}
	public Double getTotalPay() {
		return totalPay;
	}
	public void setTotalPay(Double totalPay) {
		this.totalPay = totalPay;
	}
	
	
}
