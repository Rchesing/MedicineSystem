/*
Navicat MySQL Data Transfer

Source Server         : Rachel
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : hospital

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-03-02 13:53:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed` (
  `bedNo` char(10) NOT NULL,
  `bedCost` char(20) DEFAULT NULL,
  `roomNo` char(15) DEFAULT NULL,
  PRIMARY KEY (`bedNo`),
  KEY `roonmNo1` (`roomNo`),
  CONSTRAINT `roonmNo1` FOREIGN KEY (`roomNo`) REFERENCES `room` (`roomNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for docadvice
-- ----------------------------
DROP TABLE IF EXISTS `docadvice`;
CREATE TABLE `docadvice` (
  `Advi_No` char(15) NOT NULL,
  `Sick_No` char(15) DEFAULT NULL,
  `Doc_No` char(16) DEFAULT NULL,
  `Advi_ontime` char(10) DEFAULT NULL,
  `Advi_outtime` char(10) DEFAULT NULL,
  `Advi_content` char(100) DEFAULT NULL,
  `Advi_fushu` char(100) DEFAULT NULL,
  `Advi_outpers` char(15) DEFAULT NULL,
  PRIMARY KEY (`Advi_No`),
  KEY `doc_no1` (`Doc_No`),
  KEY `sickNo` (`Sick_No`),
  CONSTRAINT `doc_no1` FOREIGN KEY (`Doc_No`) REFERENCES `doctor` (`docNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sickNo` FOREIGN KEY (`Sick_No`) REFERENCES `sick` (`Sick_No`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for doctor
-- ----------------------------
DROP TABLE IF EXISTS `doctor`;
CREATE TABLE `doctor` (
  `docNo` char(15) NOT NULL,
  `docName` char(10) NOT NULL,
  `docPassword` char(20) NOT NULL,
  `officeNo` char(20) DEFAULT NULL,
  `Doc_age` char(4) DEFAULT NULL,
  `Doc_sex` char(4) DEFAULT NULL,
  `Doc_birth` char(10) DEFAULT NULL,
  `Doc_time` char(10) DEFAULT NULL,
  `Doc_zhicheng` char(20) DEFAULT NULL,
  `Doc_xueli` char(10) DEFAULT NULL,
  `Doc_school` char(20) DEFAULT NULL,
  `Doc_address` char(20) DEFAULT NULL,
  `Telephone` char(10) DEFAULT NULL,
  `E_mail` char(25) DEFAULT NULL,
  `Remark` char(100) DEFAULT NULL,
  PRIMARY KEY (`docNo`),
  KEY `docName` (`docName`),
  KEY `officeNO3` (`officeNo`),
  CONSTRAINT `officeNO3` FOREIGN KEY (`officeNo`) REFERENCES `room` (`officeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for foregift
-- ----------------------------
DROP TABLE IF EXISTS `foregift`;
CREATE TABLE `foregift` (
  `accountNo` char(20) NOT NULL,
  `hand` char(10) DEFAULT NULL,
  `cost` char(10) DEFAULT NULL,
  `leave` char(10) DEFAULT NULL,
  `need` char(10) DEFAULT NULL,
  `medicalNo` varchar(30) NOT NULL,
  PRIMARY KEY (`accountNo`),
  KEY `medicine_no` (`medicalNo`),
  CONSTRAINT `medicine_no` FOREIGN KEY (`medicalNo`) REFERENCES `sick` (`medicalNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for room
-- ----------------------------
DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `roomNo` char(15) NOT NULL,
  `officeNo` char(20) NOT NULL,
  PRIMARY KEY (`roomNo`),
  KEY `officeNo` (`officeNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sick
-- ----------------------------
DROP TABLE IF EXISTS `sick`;
CREATE TABLE `sick` (
  `Sick_No` char(15) NOT NULL,
  `medicalNo` char(50) NOT NULL,
  `sickName` char(10) DEFAULT NULL,
  `sex` char(10) DEFAULT NULL,
  `age` char(10) DEFAULT NULL,
  `medTime` char(15) DEFAULT NULL,
  `birthday` char(15) DEFAULT NULL,
  `docNo` char(15) NOT NULL,
  `roomNo` char(15) DEFAULT NULL,
  `bedNo` char(10) DEFAULT NULL,
  `Telephone` char(10) DEFAULT NULL,
  PRIMARY KEY (`medicalNo`,`Sick_No`),
  KEY `docNo` (`docNo`),
  KEY `roomNO1` (`roomNo`),
  KEY `bedNo1` (`bedNo`),
  KEY `sickName` (`sickName`),
  KEY `Sick_No` (`Sick_No`),
  CONSTRAINT `bedNo1` FOREIGN KEY (`bedNo`) REFERENCES `bed` (`bedNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `docNo` FOREIGN KEY (`docNo`) REFERENCES `doctor` (`docNo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roomNO1` FOREIGN KEY (`roomNo`) REFERENCES `room` (`roomNo`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sickcase
-- ----------------------------
DROP TABLE IF EXISTS `sickcase`;
CREATE TABLE `sickcase` (
  `docName` char(10) NOT NULL,
  `dateT` char(10) DEFAULT NULL,
  `clinic` char(30) DEFAULT NULL,
  `outhosp` char(30) DEFAULT NULL,
  `inhosp` char(30) DEFAULT NULL,
  `sickName` char(50) NOT NULL,
  `pathology` char(30) DEFAULT NULL,
  `caseID` char(15) DEFAULT NULL,
  KEY `docName` (`docName`),
  KEY `sickName` (`sickName`),
  CONSTRAINT `docName` FOREIGN KEY (`docName`) REFERENCES `doctor` (`docName`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `sickName` FOREIGN KEY (`sickName`) REFERENCES `sick` (`sickName`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
