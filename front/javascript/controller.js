/**
 * Created by stefan on 16-4-14.
 */
define(['modules'], function(app) {
    app.controller('purchaseController', function($scope, $state, $stateParams, $filter, purchaseService, userService, payBillDetailService, commonService) {
        $scope.entity = {};

        var formatPriceFilter = $filter('formatPriceFilter');

        userService.list().then(
            function(res) {
                $scope.userList = res.data.data;
            }
        );

        $scope.toggleSelect = function(user) {
            user.selected = !user.selected;
        }

        //录入
        $scope.save = function() {
            $scope.entity.userIds = [];
            angular.forEach($scope.userList, function(p, index) {
                if (p.selected) {
                    $scope.entity.userIds.push(p.id);
                }
            });
            $scope.entity.payAt = $scope.entity.payAt.getTime();
            purchaseService.save($scope.entity).then(
                function(res) {
                    $state.go('recordList');
                },
                function(rej) {

                }
            )['finally'](function() {
                $scope.entity = {};
                angular.forEach($scope.userList, function(p, index) {
                    p.selected = false;
                });
            });
        };

        $stateParams.isAdd && $scope.$emit('displaySave', $scope.save);

        //周账单查询
        $scope.params = {};
        $scope.weekBillQuery = function() {
            $scope.weekdayList = []; //"周日","周一","周二","周三","周四","周五","周六"
            $scope.itemList = [{ name: "参与人", list: new Array(7) },
                { name: "金额", list: new Array(7) },
                { name: "人均", list: new Array(7) }
            ];

            if (!$scope.payWeekBill) { //默认查询本周账单
                //获取本周的第一天（周日）以及下周第一天
                $scope.time = new Date();
                $scope.firstDayOfCurWeek = commonService.startOfDate(commonService.dateAdd($scope.time, -($scope.time.getDay())));
                $scope.endPlusOneDayOfCurWeek = commonService.startOfDate(commonService.dateAdd($scope.firstDayOfCurWeek, 7));
                $scope.params.createStartDate = $scope.firstDayOfCurWeek.getTime();
                $scope.params.createEndDate = $scope.endPlusOneDayOfCurWeek.getTime();
            }

            for (var date = $scope.params.createStartDate; date < $scope.params.createEndDate; date = commonService.startOfDate(commonService.dateAdd(date, 1))) {
                var dateObj = new Date(date);
                $scope.weekdayList.push({
                    weekDay: dateObj.getDay(),
                    dateTime: date
                })
            }

            payBillDetailService.getPayWeekBill($scope.params).then(
                function(res) {
                    $scope.payWeekBill = res.data.data;
                    $scope.totalPayAmount = 0;
                    //填充itemList并总计金额
                    angular.forEach($scope.payWeekBill.purchaseList, function(p, index) {
                        //设置参与人信息
                        var d = new Date(p.payAt);
                        for (var j = 0; j < p.userList.length; j++) {
                            var u = p.userList[j];
                            if (j == 0) {
                                $scope.itemList[0].list[d.getDay()] = $scope.itemList[0].list[d.getDay()] ? $scope.itemList[0].list[d.getDay()] + u.name : u.name;
                            } else {
                                $scope.itemList[0].list[d.getDay()] += ", " + u.name;
                            }
                        }
                        //设置金额
                        $scope.itemList[1].list[d.getDay()] = formatPriceFilter(p.totalRealPay);
                        //设置人均
                        $scope.itemList[2].list[d.getDay()] = formatPriceFilter(p.totalRealPay / p.userList.length);
                        $scope.totalPayAmount += p.totalRealPay;
                    });
                    //未缴款人信息
                    $scope.userDuePayList = $scope.payWeekBill.userDuePayList;
                    //控制上一周下一周的按钮
                    $scope.hasNext = $scope.payWeekBill.hasNext == 1;
                    $scope.hasPrevious = $scope.payWeekBill.hasPrevious == 1;
                }
            );
        };
        $scope.weekBillQuery();

        $scope.previous = function() {
            if ($scope.hasPrevious) {
                $scope.params.createEndDate = $scope.params.createStartDate;
                $scope.params.createStartDate = commonService.startOfDate(commonService.dateAdd($scope.params.createEndDate, -7)).getTime();
                $scope.weekBillQuery();
            }
        }

        $scope.next = function() {
            if ($scope.hasNext) {
                $scope.params.createStartDate = $scope.params.createEndDate;
                $scope.params.createEndDate = commonService.startOfDate(commonService.dateAdd($scope.params.createStartDate, 7)).getTime();
                $scope.weekBillQuery();
            }
        }

    }).controller('payBillDetailController', function($scope, $modal, payBillDetailService, commonService) {
        $scope.params = {};
        $scope.page = 1;
        $scope.size = 5;
        $scope.getPayBillWeekDetailList = function() {
            $scope.params.pageSize = $scope.size;
            $scope.params.pageNumber = $scope.page;
            payBillDetailService.getDetailAmountByWeek($scope.params).then(
                function(res) {
                    $scope.detailList = res.data.data.list;
                    $scope.total = res.data.data.totalRow;
                },
                function(rej) {

                }
            );
        };
        $scope.getPayBillWeekDetailList();

        $scope.pay = function(detail) {
            if (!detail) { //缴所有
                $modal.open({
                    templateUrl: "templates/partial/pay.html",
                    controller: ['$scope', function(scope) {
                        scope.params = {};
                        scope.processing = false;
                        scope.params.status = 0;
                        payBillDetailService.getDetailAmountByAllUser(scope.params).then(
                            function(res) {
                                scope.userDuePayList = res.data.data;
                                scope.params.userIds = [];
                                if (scope.userDuePayList) {
                                    angular.forEach(scope.userDuePayList, function(p, index) {
                                        scope.params.userIds.push(p.userId);
                                    });
                                }
                            }
                        );

                        scope.confirm = function() {
                            scope.processing = true;
                            payBillDetailService.confirmPayBillDetail(scope.params).then(
                                function(res) {

                                }
                            )['finally'](function() {
                                scope.processing = false;
                                scope.$close();
                                $scope.getPayBillWeekDetailList();
                            });
                        }
                    }]
                });
            } else { //按周缴费
                $modal.open({
                    templateUrl: "templates/partial/pay.html",
                    controller: ['$scope', function(scope) {
                        scope.params = {};
                        scope.processing = false;
                        scope.params.createStartDate = detail.createStartDate;
                        scope.params.createEndDate = commonService.startOfDate(commonService.dateAdd(detail.createEndDate, 1)).getTime();
                        scope.params.status = 0;
                        payBillDetailService.getDetailAmountByUserPerWeek(scope.params).then(
                            function(res) {
                                scope.userDuePayList = res.data.data;
                                scope.params.userIds = [];
                                if (scope.userDuePayList) {
                                    angular.forEach(scope.userDuePayList, function(p, index) {
                                        scope.params.userIds.push(p.userId);
                                    });
                                }
                            }
                        );

                        scope.confirm = function() {
                            scope.processing = true;
                            payBillDetailService.confirmPayBillDetail(scope.params).then(
                                function(res) {

                                }
                            )['finally'](function() {
                                scope.processing = false;
                                scope.$close();
                                $scope.getPayBillWeekDetailList();
                            });
                        }
                    }]
                });
            }

        }

    }).controller('districtMapController', function($rootScope, $scope, $q) {
        var jsKey = "e53bec2a2aac1f5f5bed082fe86f7668";
        var webKey = "040cd62656f7d70b1f4bafeb05c77a27";
        var mapUrl = location.protocol + "//webapi.amap.com/maps?v=1.3&key=" + jsKey + "&plugin=AMap.Autocomplete,AMap.Geocoder,AMap.PlaceSearch,AMap.DistrictSearch,AMap.Polygon";

        $rootScope.isMap = true;

        function loadGaodeJs() {
            var deferred = $q.defer();

            if (window.AMap) {
                deferred.resolve();
            } else {
                $.when(
                    $.getScript(mapUrl)
                    //$.getScript(addToolbarUrl)
                ).done(function() {
                    deferred.resolve();
                });
            }
            return deferred.promise;
        }
        loadGaodeJs().then(function() {
            var map = $scope.map = new AMap.Map('mapContainer', {
                resizeEnable: true,
                center: [116.30946, 39.937629],
                zoom: 3
            });
            $scope.searchFun = new AMap.DistrictSearch({ subdistrict: 1, level: 'city', showbiz: 'false', extensions:'all' });
            $scope.searchFun.search('中国', function(status, result) {
                if (status == 'complete') {
                    var country = result.districtList[0];
                    $scope.provinceList = country.districtList;
                    $scope.$apply()
                }
            });
        })

        $scope.districtChange = function(data) {
            if (data.districtList) {
                $scope.drawPlog(data);
                return;
            }
            $scope.searchFun.search(data.adcode, function(status, result) {
                if (status == 'complete') {
                    var district = result.districtList[0];
                    data.districtList = district.districtList;
                    data.boundaries = district.boundaries;
                    $scope.$apply();
                    $scope.drawPlog(data);
                }
            });
        }

        var polygons = []
        $scope.drawPlog = function(data) {
            for (var i = 0, l = polygons.length; i < l; i++) {
                polygons[i].setMap(null);
            }
            if (data) {
                var bounds = data.boundaries;
                for (var i = 0, l = bounds.length; i < l; i++) {
                    var polygon = new AMap.Polygon({
                        map: $scope.map,
                        strokeWeight: 1,
                        strokeColor: '#CC66CC',
                        fillColor: '#CCF3FF',
                        fillOpacity: 0.5,
                        path: bounds[i]
                    });
                    polygons.push(polygon);
                }
                $scope.map.setFitView(); //地图自适应
            }
        }
    })
})
