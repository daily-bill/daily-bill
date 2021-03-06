package com.daily.bill.domain.paybill;

import java.util.List;
import java.util.Map;

import com.daily.bill.domain.purchase.Purchase;
import com.daily.bill.domain.user.User;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 11:00:03 AM
*/
public class IngredientPayWeekBill {
	/**
	 * 周账单信息集合
	 */
	private List<Purchase> purchaseList;
	
	/**
	 * 参与人未缴款集合
	 */
	private List<UserDuePayBill> userDuePayList;
	
	/**
	 * 是否有下一周
	 */
	private int hasNext;
	
	/**
	 * 是否有上一周
	 */
	private int hasPrevious;
	
	
	public List<Purchase> getPurchaseList() {
		return purchaseList;
	}

	public void setPurchaseList(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}

	public int getHasNext() {
		return hasNext;
	}

	public void setHasNext(int hasNext) {
		this.hasNext = hasNext;
	}

	public int getHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(int hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public List<UserDuePayBill> getUserDuePayList() {
		return userDuePayList;
	}

	public void setUserDuePayList(List<UserDuePayBill> userDuePayList) {
		this.userDuePayList = userDuePayList;
	}
	
	

}
