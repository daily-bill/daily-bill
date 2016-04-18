package com.daily.bill.web.controller;

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
import com.daily.bill.domain.purchase.operate.PurchaseApplyParam;
import com.daily.bill.service.purchase.DailyBillPurchaseService;

/**
*@Author Jin Rongquan
*@Version Apr 18, 2016 3:39:58 PM
*/
@Controller
@RequestMapping("/purchase")
public class DailyBillPurchaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(DailyBillPurchaseController.class);
	
	@Resource
	private DailyBillPurchaseService dailyBillPurchaseService;
	
	@RequestMapping("/apply")
	@ResponseBody
	public ResultObject<String> applyPurchase(HttpServletRequest request, PurchaseApplyParam param){
		try{
			return dailyBillPurchaseService.addPurchase(param);
		}catch(Throwable t){
			logger.error("申请添加采购费用失败", t);
		}
		return ResultObjectBuilder.error(ResultCodeConstants.SYSTEM_ERROR, "申请添加采购费用失败");
	}
	
}
