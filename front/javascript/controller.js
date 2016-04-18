/**
 * Created by stefan on 16-4-14.
 */
define(['modules'], function (app) {
    app.controller('AddRecordController', function ($scope,AddRecordService) {
        $scope.$emit('displaySave', function () {
        });
        $scope.entity = {};
        $scope.userList = [
            {name:'赵桐'},
            {name:'金融泉'},
            {name:'王靖坤'},
            {name:'卢晓勇'}
        ];
        $scope.toggleSelect = function (user) {
            user.selected = !user.selected;
        }
    }).controller('RecordListController', function ($scope,RecordListService) {
        $scope.time = Date.now();
        $scope.weekdayList = [
            "周日","周一","周二","周三","周四","周五","周六"
        ];
        $scope.itemList = [
            {name: "参与人", list: ["赵桐,金融泉","金融泉,王靖坤","金融泉","金融泉","","金融泉",""]},
            {name: "金额", list: ["100","200","300","50","","100",""]},
            {name: "人均", list: ["50","100","300","50","","100",""]}
        ];
        $scope.userList = [
            {name:"赵桐", money:100},
            {name:"金融泉", money:300},
            {name:"王靖坤", money:50},
            {name:"卢晓勇", money:200}
        ]
    }).controller('RecordStatisticsController', function ($scope,RecordStatisticsService, $modal) {
        $scope.pay = function () {
            $modal.open({
                templateUrl: 'templates/partial/pay.html',
                controller: ['$scope', function (scope) {

                }]
            })
        };

        $scope.page = 1;
        $scope.size = 5;
        $scope.total = 23;
    })
})