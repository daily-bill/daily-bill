package com.daily.bill.domain.paybill.query;

import java.util.Date;

import com.daily.bill.domain.page.query.PageQuery;

/**
*@author Jin Rongquan
*@version Sun Apr 17 14:14:34 CST 2016
*/
public class IngredientPayBillDetailQuery extends PageQuery{
	private Long createStartDate;
	private Long createEndDate;
	private Integer status;
	private String groupByType;
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
	public String getGroupByType() {
		return groupByType;
	}
	public void setGroupByType(String groupByType) {
		this.groupByType = groupByType;
	}
	
}