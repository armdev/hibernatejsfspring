/*
SQLyog Ultimate v9.50 
MySQL - 5.6.17 : Database - hibtest
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hibtest` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `hibtest`;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `role` int(11) DEFAULT '0',
  `registeredDate` datetime DEFAULT NULL,
  `lastloginDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`firstname`,`lastname`,`username`,`email`,`password`,`status`,`role`,`registeredDate`,`lastloginDate`) values (23,'Anna','Manukyan','anna@mail.ru','armine@mail.ru','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2016-02-20 01:32:33',NULL),(24,'SuperAdmin','Adminsky','admin@gmail.com','admin@gmail.com','e10adc3949ba59abbe56e057f20f883e',NULL,1,'2016-02-20 01:37:18',NULL),(25,'Diana','Admin','diana@gmail.com','diana@gmail.com','e10adc3949ba59abbe56e057f20f883e',NULL,1,'2016-02-20 01:37:49',NULL),(26,'Markos','User','user@gmail.com','user@gmail.com','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2016-02-20 01:38:28',NULL),(27,'Arnold','User','arnold@gmail.com','arnold@gmail.com','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2016-02-20 01:38:54',NULL),(28,'Bernard','Usersky','bernard@gmail.com','bernard@gmail.com','e10adc3949ba59abbe56e057f20f883e',NULL,0,'2016-02-20 01:39:28',NULL);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
