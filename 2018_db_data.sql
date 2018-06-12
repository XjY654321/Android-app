/*
SQLyog v10.2 
MySQL - 5.5.19 : Database - 2018_db_data
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`2018_db_data` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `2018_db_data`;

/*Table structure for table `admininfo` */

DROP TABLE IF EXISTS `admininfo`;

CREATE TABLE `admininfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `admininfo` */

insert  into `admininfo`(`id`,`account`,`pwd`,`tel`,`addr`,`idcard`,`name`,`money`) values (16,'admin','123',NULL,NULL,NULL,NULL,NULL);

/*Table structure for table `kcinfo` */

DROP TABLE IF EXISTS `kcinfo`;

CREATE TABLE `kcinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `hh` varchar(255) DEFAULT NULL,
  `sl` varchar(255) DEFAULT NULL,
  `jg` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL DEFAULT '',
  `time` varchar(255) DEFAULT NULL,
  `rkjg` varchar(255) DEFAULT NULL,
  `new_goods` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `kcinfo` */

insert  into `kcinfo`(`id`,`hh`,`sl`,`jg`,`name`,`time`,`rkjg`,`new_goods`) values (3,'测试','227','30','qwe','2018-04-01','20','是'),(4,'测试','1000','120','ceshishi','2018-04-06','100','否'),(5,'测试','1111','12','cece','2018-04-06','11','是'),(6,'测试','111','12','iphone7','2018-04-06','11','否'),(7,'测试','1111','13','iphone8','2018-04-06','12','是'),(8,'测试','1111','14','iphone6','2018-04-06','12','否');

/*Table structure for table `lsinfo` */

DROP TABLE IF EXISTS `lsinfo`;

CREATE TABLE `lsinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` varchar(255) DEFAULT NULL,
  `kcid` varchar(255) DEFAULT NULL,
  `sl` varchar(255) DEFAULT NULL,
  `bq` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table `lsinfo` */

insert  into `lsinfo`(`id`,`time`,`kcid`,`sl`,`bq`,`name`,`mobile`) values (4,'2018-04-06','8','110','入库','小孩','18231851153'),(6,'2018-04-06','3','123','出库','但是','18231851153'),(10,'2018-04-06','6','189','出库','对的','18231851153');

/*Table structure for table `srinfo` */

DROP TABLE IF EXISTS `srinfo`;

CREATE TABLE `srinfo` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `sr` varchar(255) DEFAULT NULL,
  `time` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `srinfo` */

insert  into `srinfo`(`id`,`sr`,`time`) values (1,'12300','2018-03-31'),(2,'246','2018-03-31'),(3,'1000','2018-03-31'),(4,'15000','2018-04-01'),(5,'600','2018-04-06'),(6,'300','2018-04-06'),(7,'3000','2018-04-06'),(8,'600','2018-04-06'),(9,'3690','2018-04-06');

/*Table structure for table `userinfo` */

DROP TABLE IF EXISTS `userinfo`;

CREATE TABLE `userinfo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `account` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `tel` varchar(255) DEFAULT NULL,
  `addr` varchar(255) DEFAULT NULL,
  `idcard` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `money` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

/*Data for the table `userinfo` */

insert  into `userinfo`(`id`,`account`,`pwd`,`tel`,`addr`,`idcard`,`name`,`money`,`type`) values (1,'abc','123','137123456781','广东','1','张先生','23434','1'),(7,'wer','123','13712345678','广东','2','x先生','10','2'),(9,'haha','123','13712345678','广东','2','x小姐','10','2'),(16,'qwe','123','13712345678','ddd','1','x先生',NULL,'1'),(17,'123','123','18231851153','null',NULL,'err',NULL,'1'),(18,'456','456','456','null',NULL,'456',NULL,'1'),(19,'789','123','123','uvhhbll','','123',NULL,'2');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
