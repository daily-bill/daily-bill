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
import com.daily.bill.domain.paybill.IngredientPayBillDetailAmount;
import com.daily.bill.domain.paybill.IngredientPayWeekBill;
import com.daily.bill.domain.paybill.operate.PayBillDetailOperateParam;
import com.daily.bill.service.paybill.DailyBillPayBillDetailService;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 3:48:36 PM
*/
@Controller
@RequestMapping("/payBillDetail")
public class DailyBillPayBillDetailController {

	private static final Logger logger = LoggerFactory.getLogger(DailyBillPayBillDetailController.class);

	@Resource
	private DailyBillPayBillDetailService dailyBillPayBillDetailService;
	
	@RequestMapping("/getDetailAmountByUserPerWeek")
	@ResponseBody
	public ResultObject<List<IngredientPayBillDetailAmount>> getDetailAmountByUserPerWeek(HttpServletRequest request, PayBillDetailOperateParam param){
		try{
			return dailyBillPayBillDetailService.getDetailAmountByUserPerWeek(param);
		}catch(Throwable t){
			logger.error("获取每周每个用户的缴款单信息失败", t);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.SYSTEM_ERROR, "获取每周每个用户的缴款单信息失败");
	}
	
	@RequestMapping("/getDetailAmountByAllUser")
	@ResponseBody
	public ResultObject<List<IngredientPayBillDetailAmount>> getDetailAmountByAllUser(HttpServletRequest request, PayBillDetailOperateParam param){
		try{
			return dailyBillPayBillDetailService.getDetailAmountByAllUser(param);
		}catch(Throwable t){
			logger.error("获取每个用户的所有缴款单信息失败", t);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.SYSTEM_ERROR, "获取每个用户的所有缴款单信息失败");
	}
	
	@RequestMapping("/getPayWeekBill")
	@ResponseBody
	public ResultObject<IngredientPayWeekBill> getPayWeekBill(HttpServletRequest request, PayBillDetailOperateParam param){
		try{
			return dailyBillPayBillDetailService.getPayWeekBill(param);
		}catch(Throwable t){
			logger.error("获取周账单信息失败", t);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.SYSTEM_ERROR, "获取周账单信息失败");
	}
	
}
