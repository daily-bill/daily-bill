<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String staticHost = "http://localhost";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>伙食经费管理系统</title>
    <link type="image/x-icon" rel="shortcut icon" href="<%=staticHost %>/images/food.png">
    <link rel="stylesheet" href="<%=staticHost %>/public/angular/angular-growl.min.css" />
    <link rel="stylesheet" href="<%=staticHost %>/public/jquery/jquery-ui.css" />
    <link rel="stylesheet" href="<%=staticHost %>/public/bootstrap/bootstrap.min.css" />
    <link rel="stylesheet" href="<%=staticHost %>/public/bootstrap/bootstrap-theme.css" />
    <link rel="stylesheet" href="<%=staticHost %>/public/Font-Awesome/css/font-awesome.min.css" />
    <link rel="stylesheet" href="<%=staticHost %>/public/angular/datetimepicker/css/datetimepicker.css" />
    <link rel="stylesheet" href="<%=staticHost %>/stylesheet/style.css" />
</head>
<body>
	<nav class="nav navbar-fixed-top">
        <ul class="list-inline">
            <li class="home">
                <a href="#">
                    <i class="fa fa-home"></i>
                </a>
            </li>
            <li>
                <a href="" ui-sref="addRecord">
                    <i class="fa fa-pencil"></i>
                    <span>录入</span>
                </a>
            </li>
            <li>
                <a href="" ui-sref="recordList">
                    <i class="fa fa-list"></i>
                    <span>本周</span>
                </a>
            </li>
            <li>
                <a href=""  ui-sref="recordStatistics">
                    <i class="fa fa-outdent"></i>
                    <span>汇总</span>
                </a>
            </li>
        </ul>
        <div class="pull-right mr30" ng-if="isDisplaySave">
            <a href="" ng-click="save()">保存</a>
        </div>
    </nav>
    <div class="container">
        <div ui-view></div>
    </div>
    <nav class="nav navbar-fixed-bottom text-center">
        <span>©2016 江左盟</span>
    </nav>
    <!--requireJs-->
    <script src="<%=staticHost %>/public/requireJs/require.js" data-main="<%=staticHost %>/app.js" defer="defer"></script>
</body>
</html>