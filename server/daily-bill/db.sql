CREATE database dailybill;

use dailybill;

CREATE TABLE `ingredient_pay_bill_detail` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `due_pay` decimal(10,2) DEFAULT NULL,
  `pay_at` bigint(20) DEFAULT NULL,
  `status` smallint(6) DEFAULT NULL,
  `create_at` bigint(20) DEFAULT NULL,
  `purchase_id` smallint(6) DEFAULT NULL,
  `user_id` smallint(6) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

CREATE TABLE `purchase` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `total_real_pay` decimal(10,2) DEFAULT NULL,
  `pay_at` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` smallint(6) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;