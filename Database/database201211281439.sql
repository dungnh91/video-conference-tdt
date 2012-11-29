/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : vc

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-11-28 14:39:52
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
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_conference
-- ----------------------------
INSERT INTO tbl_vc_conference VALUES ('1', 'abc', null, null, null, null, null, null, null, '1', '2012-11-09 22:52:06');
INSERT INTO tbl_vc_conference VALUES ('2', 'def', null, null, null, null, null, null, null, '1', null);

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
  `user_id` int(10) unsigned DEFAULT NULL,
  `conference_id` int(10) unsigned DEFAULT NULL,
  `status` tinyint(3) unsigned DEFAULT NULL,
  `modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`participant_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_participant
-- ----------------------------
INSERT INTO tbl_vc_participant VALUES ('1', null, '1', '1', '2012-11-09 22:52:38');

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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_user
-- ----------------------------
