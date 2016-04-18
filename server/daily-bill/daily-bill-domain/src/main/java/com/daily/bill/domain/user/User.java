package com.daily.bill.domain.user;
import java.io.Serializable;
/**
*@author Jin Rongquan
*@version Sun Apr 17 14:18:22 CST 2016
*/
public class User implements Serializable {
	private Integer id;
	private String phone;
	private String name;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}