package com.daily.bill.service.user;

import java.util.List;

import com.daily.bill.domain.user.User;
import com.daily.bill.domain.user.query.UserQuery;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 5:24:41 AM
*/
public interface DailyBillUserService {
	public List<User> getListByQuery(UserQuery query);
	
	public User getById(Integer id);
}
