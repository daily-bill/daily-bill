/**
 * Created by stefan on 16-4-14.
 */
define(['modules'], function (app) {
// 格式化金额
app.filter('formatPriceFilter', function () {
  return function (price) {
    if(price == 0){
      return "0.00";
    }
    if (!parseFloat(price)) {
      return price;
    }
    return parseFloat(price).toFixed(2);
  };
});

//根据某一天获取这天所在星期的第一天（周日）
app.filter('getStartOfCurWeekFilter', ['$filter', function($filter){
	return function(date){
		if(!date){
			return "";
		}
		var d = new Date(date);
		if(d.getDay() != 0){
			d.setDate(d.getDate() - d.getDay());
		}
		var dateFormatFilter = $filter('date');
		return dateFormatFilter(d, 'yyyy年MM月dd日');
	}
}]);

//根据某一天获取这天所在星期的第七天（周六）
app.filter('getEndOfCurWeekFilter', ['$filter', function($filter){
	return function(date){
		if(!date){
			return "";
		}
		var d = new Date(date);
		if(d.getDay() != 6){
			d.setDate(d.getDate() - d.getDay() + 6);
		}
		var dateFormatFilter = $filter('date');
		return dateFormatFilter(d, 'yyyy年MM月dd日');
	}
}]);
})