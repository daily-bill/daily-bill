/**
 * Created by stefan on 16-4-14.
 */
define(['modules'], function (app) {
    app.factory('purchaseService', function ($http) {
    	return {
    		save : function(params){
    			return $http({
    				url: '/dailybill/purchase/apply',
    				method: 'post',
    				params: params
    			});
    		}
    	}
    }).factory('payBillDetailService', function ($http) {
    	return {
    		getPayWeekBill : function(params){
    			return $http({
    				url: '/dailybill/payBillDetail/getPayWeekBill',
    				method: 'get',
    				params: params
    			});
    		},
    		getDetailAmountByUserPerWeek : function(params){
    			return $http({
    				url: '/dailybill/payBillDetail/getDetailAmountByUserPerWeek',
    				method: 'get',
    				params: params
    			});
    		},
    		getDetailAmountByWeek : function(params){
    			return $http({
    				url: '/dailybill/payBillDetail/getDetailAmountByWeek',
    				method: 'get',
    				params: params
    			});
    		},
    		getDetailAmountByAllUser : function(params){
    			return $http({
    				url: '/dailybill/payBillDetail/getDetailAmountByAllUser',
    				method: 'get',
    				params: params
    			});
    		},
    		confirmPayBillDetail : function(params){
    			return $http({
    				url: '/dailybill/payBillDetail/confirmPayBillDetail',
    				method: 'post',
    				params: params
    			});
    		}
    		
    	}
    }).factory('userService', function ($http) {
    	return {
    		list : function(params){
        		return $http({
        			url: '/dailybill/user/list',
        			method: 'get',
        			params: params
        		});
        	}
    	}
    	
    }).factory('commonService', function ($http) {
    	return {
    		dateAdd : function(date, days){
    			var d=new Date(date); 
    			d.setDate(d.getDate()+days); 
    			return d; 
        	},
        	startOfDate : function(date){
        		var d = new Date(date);
        		d.setHours(0);
        		d.setMinutes(0);
        		d.setSeconds(0);
        		d.setMilliseconds(0);
        		return d;
        	}
    	}
    })
})