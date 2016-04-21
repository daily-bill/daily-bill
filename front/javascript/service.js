/**
 * Created by stefan on 16-4-14.
 */
define(['modules'], function (app) {
    app.factory('purchaseService', function ($http, project) {
    	return {
    		save : function(params){
    			return $http({
    				url: project.url + 'purchase/apply',
    				method: 'post',
    				params: params
    			});
    		}
    	}
    }).factory('payBillDetailService', function ($http,project) {
    	return {
    		getPayWeekBill : function(params){
    			return $http({
    				url: project.url + 'payBillDetail/getPayWeekBill',
    				method: 'get',
    				params: params
    			});
    		},
    		getDetailAmountByUserPerWeek : function(params){
    			return $http({
    				url: project.url + 'payBillDetail/getDetailAmountByUserPerWeek',
    				method: 'get',
    				params: params
    			});
    		},
    		getDetailAmountByWeek : function(params){
    			return $http({
    				url: project.url + 'payBillDetail/getDetailAmountByWeek',
    				method: 'get',
    				params: params
    			});
    		},
    		getDetailAmountByAllUser : function(params){
    			return $http({
    				url: project.url + 'payBillDetail/getDetailAmountByAllUser',
    				method: 'get',
    				params: params
    			});
    		},
    		confirmPayBillDetail : function(params){
    			return $http({
    				url: project.url + 'payBillDetail/confirmPayBillDetail',
    				method: 'post',
    				params: params
    			});
    		}
    		
    	}
    }).factory('userService', function ($http, project) {
    	return {
    		list : function(params){
        		return $http({
        			url: project.url + 'user/list',
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