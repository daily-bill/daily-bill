package com.daily.bill.domain.query;

import java.io.Serializable;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 10:44:52 AM
*/
public class BaseQuery implements Serializable{
	private int isDel = 0;

	public int getIsDel() {
		return isDel;
	}

	public void setIsDel(int isDel) {
		this.isDel = isDel;
	}
}
