/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : video_conference

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-12-04 12:47:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tbl_vc_conference`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vc_conference`;
CREATE TABLE `tbl_vc_conference` (
  `conference_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `conference_name` varchar(255) DEFAULT NULL,
  `date` datetime DEFAULT NULL,
  `time_start` time DEFAULT NULL,
  `time_end` time DEFAULT NULL,
  `host_id` int(10) unsigned DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `participant` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`conference_id`)
) ENGINE=MyISAM AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_conference
-- ----------------------------
INSERT INTO `tbl_vc_conference` VALUES ('37', 'fdafds', '2012-12-18 00:00:00', '03:00:14', '01:00:14', '1', 'fas', null, '4', '1', '2012-12-01 23:30:14');
INSERT INTO `tbl_vc_conference` VALUES ('38', 'fteag', '2012-12-19 00:00:00', '04:00:12', '00:00:12', '1', 'teatg', null, '0', '1', null);
INSERT INTO `tbl_vc_conference` VALUES ('39', 'fteag', '2012-12-19 00:00:00', '05:00:35', '02:00:35', '1', 'teatg', null, '2', '1', '2012-12-01 16:24:36');
INSERT INTO `tbl_vc_conference` VALUES ('40', 'tew', '2012-12-25 00:00:00', '06:00:31', '01:00:31', '1', 'create', null, '3', '1', null);
INSERT INTO `tbl_vc_conference` VALUES ('41', 'create123', '2012-12-29 00:00:00', '06:00:14', '02:00:14', '1', 'create2', null, '2', '1', '2012-12-01 23:37:15');
INSERT INTO `tbl_vc_conference` VALUES ('42', 'teat', '2012-12-10 00:00:00', '03:00:54', '00:00:54', '1', 'tea', null, '4', '1', '2012-12-01 16:25:54');
INSERT INTO `tbl_vc_conference` VALUES ('43', 'tsts', '2012-12-12 00:00:00', '04:00:48', '00:00:48', '1', 'fsfasf', null, '2', '1', null);
INSERT INTO `tbl_vc_conference` VALUES ('44', 'testesf', null, null, null, '0', null, null, '0', '1', '2012-12-02 01:26:23');
INSERT INTO `tbl_vc_conference` VALUES ('45', 'test', '2012-12-03 00:00:00', '05:00:37', '02:00:37', '2', 'test', null, '2', '1', '2012-12-02 16:36:37');
INSERT INTO `tbl_vc_conference` VALUES ('46', null, null, null, null, '0', null, null, '0', '0', '2012-12-03 00:50:31');
INSERT INTO `tbl_vc_conference` VALUES ('48', 'test', '2012-12-11 00:00:00', '04:00:42', '00:00:42', '2', '123123', null, '4', '1', null);
INSERT INTO `tbl_vc_conference` VALUES ('47', null, null, null, null, '0', null, null, '0', '0', '2012-12-03 00:47:06');

-- ----------------------------
-- Table structure for `tbl_vc_emailqueue`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vc_emailqueue`;
CREATE TABLE `tbl_vc_emailqueue` (
  `email_queue_id` int(1) unsigned NOT NULL AUTO_INCREMENT,
  `from` varchar(50) DEFAULT NULL,
  `to` varchar(50) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `headers` varchar(50) DEFAULT NULL,
  `cc` varchar(50) DEFAULT NULL,
  `bcc` varchar(50) DEFAULT NULL,
  `descriptions` varchar(50) DEFAULT NULL,
  `ip_add` varchar(15) DEFAULT '0.0.0.0',
  `status` smallint(1) unsigned DEFAULT '1',
  PRIMARY KEY (`email_queue_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_emailqueue
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_vc_friendlist`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vc_friendlist`;
CREATE TABLE `tbl_vc_friendlist` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned DEFAULT NULL,
  `friend_id` int(10) unsigned DEFAULT NULL,
  `modify` date DEFAULT NULL,
  `status` smallint(5) unsigned DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_friendlist
-- ----------------------------

-- ----------------------------
-- Table structure for `tbl_vc_participant`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vc_participant`;
CREATE TABLE `tbl_vc_participant` (
  `participant_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `host_id` int(10) DEFAULT NULL,
  `user_id` int(10) unsigned DEFAULT NULL,
  `conference_id` int(10) unsigned DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT '1',
  `modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`participant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_participant
-- ----------------------------
INSERT INTO `tbl_vc_participant` VALUES ('51', '2', '5', '46', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('8', '1', '3', '37', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('9', '1', '4', '37', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('10', '1', '3', '38', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('11', '1', '5', '38', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('48', '2', '1', '46', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('24', '1', '3', '39', '1', '2012-12-01 16:24:23');
INSERT INTO `tbl_vc_participant` VALUES ('50', '2', '4', '46', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('16', '1', '5', '41', '0', '2012-12-01 23:29:17');
INSERT INTO `tbl_vc_participant` VALUES ('17', '1', '6', '41', '0', '2012-12-01 23:29:17');
INSERT INTO `tbl_vc_participant` VALUES ('25', '1', '4', '39', '1', '2012-12-01 16:24:23');
INSERT INTO `tbl_vc_participant` VALUES ('33', '1', '5', '39', '0', '2012-12-01 16:24:36');
INSERT INTO `tbl_vc_participant` VALUES ('27', '1', '6', '39', '0', '2012-12-01 16:24:36');
INSERT INTO `tbl_vc_participant` VALUES ('49', '2', '3', '46', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('36', '1', '3', '42', '1', '2012-12-01 16:25:47');
INSERT INTO `tbl_vc_participant` VALUES ('37', '1', '5', '42', '0', '2012-12-01 16:25:55');
INSERT INTO `tbl_vc_participant` VALUES ('38', '1', '6', '42', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('39', '1', '4', '42', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('40', '1', '2', '43', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('41', '1', '4', '43', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('42', '1', '2', '44', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('43', '1', '5', '37', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('44', '1', '3', '41', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('45', '1', '4', '41', '0', '2012-12-01 23:37:15');
INSERT INTO `tbl_vc_participant` VALUES ('46', '2', '1', '45', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('47', '2', '3', '45', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('52', '2', '3', '47', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('53', '2', '4', '47', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('54', '2', '5', '47', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('55', '2', '3', '48', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('56', '2', '4', '48', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('57', '2', '5', '48', '1', null);
INSERT INTO `tbl_vc_participant` VALUES ('58', '2', '6', '48', '1', null);

-- ----------------------------
-- Table structure for `tbl_vc_user`
-- ----------------------------
DROP TABLE IF EXISTS `tbl_vc_user`;
CREATE TABLE `tbl_vc_user` (
  `user_id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `fullname` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `avartar` varchar(255) DEFAULT NULL,
  `subcriptions` tinyint(3) unsigned DEFAULT '1',
  `modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_user
-- ----------------------------
INSERT INTO `tbl_vc_user` VALUES ('1', 'Dung', 'dungnh', '25d55ad283aa400af464c76d713c07ad', 'dungnh@gmail.com', 'test', '3', '2012-12-01 21:48:09');
INSERT INTO `tbl_vc_user` VALUES ('2', 'Canh', 'duka', '25d55ad283aa400af464c76d713c07ad', 'duka@gmail.com', 'test', '1', null);
INSERT INTO `tbl_vc_user` VALUES ('3', 'Mai', 'mai.cao', '25d55ad283aa400af464c76d713c07ad', 'mai.khong.cao@gmail.com', 'test', '1', null);
INSERT INTO `tbl_vc_user` VALUES ('4', 'test', 'user4', null, null, null, '1', '2012-12-01 13:03:09');
INSERT INTO `tbl_vc_user` VALUES ('5', 'test1', 'user5', null, null, null, '1', '2012-12-01 13:03:12');
INSERT INTO `tbl_vc_user` VALUES ('6', 'da', 'user6', null, null, null, '1', '2012-12-01 13:03:15');
INSERT INTO `tbl_vc_user` VALUES ('8', 'dfafdsaf', '123', '25d55ad283aa400af464c76d713c07ad', 'fdsajflk@mail.com', 'test', '1', null);
