package com.daily.bill.domain.paybill.operate;
/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 9:49:13 AM
*/
public class PayBillDetailOperateParam {
	private Long createStartDate;
	private Long createEndDate;
	private Integer status;
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
	
}
