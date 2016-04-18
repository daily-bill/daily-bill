package com.daily.bill.service.user.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.daily.bill.dal.user.UserDao;
import com.daily.bill.domain.user.User;
import com.daily.bill.domain.user.query.UserQuery;
import com.daily.bill.service.user.DailyBillUserService;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 5:26:35 AM
*/
@Service("dailyBillUserService")
public class DailyBillUserServiceImpl implements DailyBillUserService {

	@Resource
	private UserDao userDao;
	
	@Override
	public List<User> getListByQuery(UserQuery query) {
		return userDao.getListByQuery(query);
	}

	@Override
	public User getById(Integer id) {
		return userDao.getById(id);
	}

}
