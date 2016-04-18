package com.daily.bill.common.model;
/**
*@Author Jin Rongquan
*@Version Apr 17, 2016 3:59:12 PM
*/
public class ResultObjectBuilder {
	public static  <T> ResultObject<T> error(int code, String message) {
		ResultObject<T> result = new ResultObject<T>();
		result.setCode(code);
		result.setMessage(message);
		result.setSuccess(false);
		return result;
	}

    public static  <T> ResultObject<T> errorWithData(int code, String message, T data) {
        ResultObject<T> result = new ResultObject<T>();
        result.setCode(code);
        result.setData(data);
        result.setMessage(message);
        result.setSuccess(false);
        return result;
    }

	public static <T> ResultObject<T> error(String message) {
		ResultObject<T> result = new ResultObject<T>();
		result.setMessage(message);
		result.setSuccess(false);
		return result;
	}
	
	public static <T> ResultObject<T> success(T data) {
		ResultObject<T> result = new ResultObject<T>();
		result.setData(data);
		result.setSuccess(true);
		return result;
	}

    public static <T> ResultObject<T> successWithCode(T data) {
        ResultObject<T> result = new ResultObject<T>();
        result.setData(data);
        result.setSuccess(true);
        result.setCode(ResultCodeConstants.SUCCESS);
        return result;
    }
	
	public static <T> ResultObject<T> success(int code, T data) {
		ResultObject<T> result = new ResultObject<T>();
		result.setCode(code);
		result.setData(data);
		result.setSuccess(true);
		return result;
	}

    public static <T> ResultObject<T> successWithCode(T data, String message) {
        ResultObject<T> result = new ResultObject<T>();
        result.setData(data);
        result.setSuccess(true);
        result.setMessage(message);
        result.setCode(ResultCodeConstants.SUCCESS);
        return result;
    }
}
