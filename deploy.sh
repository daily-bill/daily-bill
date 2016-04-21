#!/bin/sh
echo "git pull start*********"
#git pull
echo "git pull end**********"

#前端目录
PATH_FRONT=front
#server根目录
SERVER_ROOT=server/daily-bill
#配置所在目录
CONFIG_DIR=xxx
CONFIG_ROOT=${SERVER_ROOT}/daily-bill-dal/src/main/resources/config/daily.bill.db.properties
#要部署的项目
PROJECT_ROOT=${SERVER_ROOT}/daily-bill-web
#要部署的项目的webapp目录
PATH_SRC_ROOT=${PROJECT_ROOT}/src/main/webapp
#要部署的项目maven编译后的war包解压的目录
PATH_TARGET_ROOT=${PROJECT_ROOT}/target/adminbg
#部署的地方
WEB_ROOT=${PROJECT_ROOT}/target/webroot
#tomcat目录
TOMCAT_ROOT=xxx

set -e

export PATH

echo "copy front src start*******"

if [ -d "${PATH_SRC_ROOT}/html" ]; then
    printf '%s\n' "remove front dir: $PATH_SRC_ROOT/html/*"
    rm -rf $PATH_SRC_ROOT/html
fi

mkdir ${PATH_SRC_ROOT}/html

cp -rp $PATH_FRONT/* $PATH_SRC_ROOT/html

echo "copy front src end*******"

echo "copy config file start******"

if [ -d "$CONFIG_ROOT" ]; then
    rm -rf $CONFIG_ROOT
fi
mkdir $CONFIG_ROOT

cp -fr ${CONFIG_DIR}/* $CONFIG_ROOT


echo "copy config file end******"

echo "request path add html/ start*****"

sed -i "s/stylesheet\//html\/stylesheet\//g" `grep "stylesheet\/" -rl ${PATH_SRC_ROOT}/html`
sed -i "s/images\//html\/images\//g" `grep "images\/" -rl ${PATH_SRC_ROOT}/html`
#sed -i "s/javascript\//html\/javascript\//g" `grep "javascript\/" -rl ${PATH_SRC_ROOT}/html`
sed -i "s/public\//html\/public\//g" `grep "public\/" -rl ${PATH_SRC_ROOT}/html/index.html`
sed -i "s/templates\//html\/templates\//g" `grep "templates\/" -rl ${PATH_SRC_ROOT}/html`
sed -i "s/app\.js/html\/app\.js/i" `grep "app\.js" -rl ${PATH_SRC_ROOT}/html/index.html`
sed -i "s/\/dailybillbg\///i" `grep "\/dailybillbg\/" -rl ${PATH_SRC_ROOT}/html/javascript/config.js`

echo "request path add html/ end*****"

echo "maven package start*******"

mvn clean compile package -U -Dmaven.test.skip=true

echo "maven package end*******"

echo "deploy start****"
mkdir -p ${WEB_ROOT}


cp -fr ${PATH_TARGET_ROOT}/* $WEB_ROOT

#sh $TOMCAT_ROOT/bin/catalina.sh stop
#sh $TOMCAT_ROOT/bin/catalina.sh start

echo "deploy end****"
