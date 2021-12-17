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

 Date: 17/12/2021 18:37:55
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for history_subcriber
-- ----------------------------
DROP TABLE IF EXISTS `history_subcriber`;
CREATE TABLE `history_subcriber`  (
  `id` int(11) NOT NULL,
  `id_subcriber` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tanggal_kirim` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jam_kirim` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of history_subcriber
-- ----------------------------

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `group_param` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kode_param` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `param` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES (1, 'SMS-TWILIO', 'sid', 'AC1eff6d3633fad6a26477f1da516ce918');
INSERT INTO `parameter` VALUES (2, 'SMS-TWILIO', 'token', '3f77d92b23395f3a026826c0f483bf35');
INSERT INTO `parameter` VALUES (3, 'SMS-TWILIO', 'number.from', '+14405776422');
INSERT INTO `parameter` VALUES (4, 'EMAIL-GMAIL', 'from.name', 'Irawan Papang Subakti');
INSERT INTO `parameter` VALUES (5, 'EMAIL-GMAIL', 'from.reply', 'no_reply@example.com');
INSERT INTO `parameter` VALUES (6, 'EMAIL-GMAIL', 'to.reply', 'no_reply@example.com');
INSERT INTO `parameter` VALUES (7, 'EMAIL-GMAIL', 'from.email', 'irawanpapangsubakti28@gmail.com');
INSERT INTO `parameter` VALUES (8, 'EMAIL-GMAIL', 'from.password', '28papang');
INSERT INTO `parameter` VALUES (10, 'SCHEDULED-CRON', 'cron.expression', '2021-12-18 17:41:50');
INSERT INTO `parameter` VALUES (11, 'SCHEDULED-CRON', 'cron.subject', 'Fix Digital TERRR Irawan Papang Subakti');
INSERT INTO `parameter` VALUES (12, 'SCHEDULED-CRON', 'cron.messasge', 'Coba kirim Message');

-- ----------------------------
-- Table structure for subscriber
-- ----------------------------
DROP TABLE IF EXISTS `subscriber`;
CREATE TABLE `subscriber`  (
  `id` varchar(100) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL,
  `nama` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `nohp` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tipe` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `last_kirim` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of subscriber
-- ----------------------------
INSERT INTO `subscriber` VALUES ('SUB20211217113226878871', 'Anja An', 'irawanpapangsubakti@gmail.com', '0838323241213', 'EMAIL', NULL);
INSERT INTO `subscriber` VALUES ('SUB20211217164918542141', 'Anja An', 'cupankcapgopek@gmail.com', '0838323241213', 'EMAIL', NULL);

SET FOREIGN_KEY_CHECKS = 1;
