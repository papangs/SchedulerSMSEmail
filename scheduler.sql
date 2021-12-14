/*
 Navicat Premium Data Transfer

 Source Server         : Localhost
 Source Server Type    : MySQL
 Source Server Version : 50625
 Source Host           : localhost:3306
 Source Schema         : scheduler

 Target Server Type    : MySQL
 Target Server Version : 50625
 File Encoding         : 65001

 Date: 14/12/2021 15:00:22
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for subscriber
-- ----------------------------
DROP TABLE IF EXISTS `subscriber`;
CREATE TABLE `subscriber`  (
  `id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nohp` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `last_kirim` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of subscriber
-- ----------------------------
INSERT INTO `subscriber` VALUES ('Sub001', 'pank', 'dad', 'dasas', 'ads', '1232');

SET FOREIGN_KEY_CHECKS = 1;
