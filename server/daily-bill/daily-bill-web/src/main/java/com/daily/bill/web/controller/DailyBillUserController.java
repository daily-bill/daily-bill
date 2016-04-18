package com.daily.bill.web.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.daily.bill.common.model.ResultCodeConstants;
import com.daily.bill.common.model.ResultObject;
import com.daily.bill.common.model.ResultObjectBuilder;
import com.daily.bill.domain.user.User;
import com.daily.bill.domain.user.query.UserQuery;
import com.daily.bill.service.user.DailyBillUserService;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 3:44:47 PM
*/
@Controller
@RequestMapping("/user")
public class DailyBillUserController {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyBillUserController.class);
	
	@Resource
	private DailyBillUserService dailyBillUserService;
	
	@RequestMapping("/list")
	@ResponseBody
	public ResultObject<List<User>> getUserList(HttpServletRequest request, UserQuery query){
		try{
			List<User> list = dailyBillUserService.getListByQuery(query);
			return ResultObjectBuilder.successWithCode(list);
		}catch(Throwable t){
			logger.error("获取用户信息失败", t);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.SYSTEM_ERROR, "获取用户信息失败");
	}

}
