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
            templateUrl: 'templates/welcome.html',
         }).state('addRecord',{
             url:'/addRecord',
             templateUrl: 'templates/addRecord.html',
             controller: 'AddRecordController'
         }).state('recordList',{
             url:'/recordList',
             templateUrl: 'templates/recordList.html',
             controller: 'RecordListController'
         }).state('recordStatistics',{
             url:'/recordStatistics',
             templateUrl: 'templates/recordStatistics.html',
             controller: 'RecordStatisticsController'
         })
   })
});