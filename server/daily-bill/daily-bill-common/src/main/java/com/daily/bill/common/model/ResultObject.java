package com.daily.bill.common.model;

import java.io.Serializable;

/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:59:21 PM
*/
public class ResultObject<T> implements Serializable{

	private String message;
	
	private int code;
	
	private T data;
	
	private boolean success;
	
	public ResultObject() {
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ResultObject [message=" + message + ", code=" + code + ", data=" + data + ", success=" + success + "]";
	}

}
