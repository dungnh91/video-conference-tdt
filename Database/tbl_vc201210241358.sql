/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50524
Source Host           : localhost:3306
Source Database       : vc

Target Server Type    : MYSQL
Target Server Version : 50524
File Encoding         : 65001

Date: 2012-10-24 13:58:47
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
  `description` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `participant` int(11) DEFAULT NULL,
  `status` tinyint(4) DEFAULT '1',
  `modify` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`conference_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_conference
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
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tbl_vc_participant
-- ----------------------------

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
