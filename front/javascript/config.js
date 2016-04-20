/**
 * Created by stefan on 16-4-14.
 */
define(['modules'], function (app) {
    app.run(function ($rootScope) {
       $rootScope.$on('$stateChangeStart', function () {
           $rootScope.isDisplaySave = false;
           $rootScope.save = null;
       });
        $rootScope.$on('displaySave',function(event,callback){
            $rootScope.isDisplaySave = true;
            $rootScope.save = callback;
        })
    });
   app.config(function ($stateProvider, $urlRouterProvider) {
      $urlRouterProvider.otherwise('/welcome');
      $stateProvider
         .state('welcome',{
             url:'/welcome',
            templateUrl: 'http://localhost/dailybill/templates/welcome.html',
         }).state('addRecord',{
             url:'/addRecord',
             templateUrl: 'http://localhost/dailybill/templates/addRecord.html',
             controller: 'purchaseController'
         }).state('recordList',{
             url:'/recordList',
             templateUrl: 'http://localhost/dailybill/templates/recordList.html',
             controller: 'purchaseController'
         }).state('recordStatistics',{
             url:'/recordStatistics',
             templateUrl: 'http://localhost/dailybill/templates/recordStatistics.html',
             controller: 'payBillDetailController'
         })
   }).config(function (paginationConfig) { //分页配置
        paginationConfig.directionLinks = true;
        paginationConfig.boundaryLinks = true; //是否显示首页，尾页选项
        paginationConfig.maxSize = 5; //最多显示几页的选项
        paginationConfig.firstText = '首页';
        paginationConfig.lastText = '尾页';
        paginationConfig.previousText = '上一页';
        paginationConfig.nextText = '下一页';
    });
});