/*
 Navicat Premium Data Transfer

 Source Server         : Mysql
 Source Server Type    : MySQL
 Source Server Version : 100421
 Source Host           : localhost:3306
 Source Schema         : scheduler

 Target Server Type    : MySQL
 Target Server Version : 100421
 File Encoding         : 65001

 Date: 19/12/2021 09:09:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for history_subcriber
-- ----------------------------
DROP TABLE IF EXISTS `history_subcriber`;
CREATE TABLE `history_subcriber`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_subcriber` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `tanggal_kirim` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `jam_kirim` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of history_subcriber
-- ----------------------------
INSERT INTO `history_subcriber` VALUES (1, 'SUB20211217113226878871', '2021-12-20', '22:55:01', 'SUKSES');
INSERT INTO `history_subcriber` VALUES (2, 'SUB20211217164918542141', '2021-12-20', '22:55:01', 'GAGAL');

-- ----------------------------
-- Table structure for parameter
-- ----------------------------
DROP TABLE IF EXISTS `parameter`;
CREATE TABLE `parameter`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_param` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `kode_param` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL DEFAULT NULL,
  `param` text CHARACTER SET latin1 COLLATE latin1_swedish_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of parameter
-- ----------------------------
INSERT INTO `parameter` VALUES (1, 'SMS-TWILIO', 'sid', 'AC1eff6d3633fad6a26477f1da516ce918');
INSERT INTO `parameter` VALUES (2, 'SMS-TWILIO', 'token', '3f77d92b23395f3a026826c0f483bf35');
INSERT INTO `parameter` VALUES (3, 'SMS-TWILIO', 'number.from', '+14405776422 afsaf');
INSERT INTO `parameter` VALUES (4, 'EMAIL-GMAIL', 'from.name', 'Irawan Papang Subakti sfdadagsdad');
INSERT INTO `parameter` VALUES (5, 'EMAIL-GMAIL', 'from.reply', 'no_reply@example.com');
INSERT INTO `parameter` VALUES (6, 'EMAIL-GMAIL', 'to.reply', 'no_reply@example.com');
INSERT INTO `parameter` VALUES (7, 'EMAIL-GMAIL', 'from.email', 'irawanpapangsubakti28@gmail.com');
INSERT INTO `parameter` VALUES (8, 'EMAIL-GMAIL', 'from.password', '28papangs');
INSERT INTO `parameter` VALUES (10, 'SCHEDULED-CRON', 'cron.expression', '2021-12-20 22:55:01');
INSERT INTO `parameter` VALUES (11, 'EMAIL-GMAIL', 'subject', 'Fix Digital TERRR Irawan Papang Subakti');
INSERT INTO `parameter` VALUES (12, 'SCHEDULED-CRON', 'cron.messasge', 'Coba kirim Message dsafaefgqqwdsad');
INSERT INTO `parameter` VALUES (13, 'THREAD', 'setting.thread', '1000');
INSERT INTO `parameter` VALUES (14, 'THREAD', 'format.date', 'yyyy-MM-dd HH:mm:ss');

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
) ENGINE = InnoDB CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = COMPACT;

-- ----------------------------
-- Records of subscriber
-- ----------------------------
INSERT INTO `subscriber` VALUES ('SUB20211217113226878871', 'Anja An', 'irawanpapangsubakti@gmail.com', '+62838323241213', 'EMAIL', NULL);
INSERT INTO `subscriber` VALUES ('SUB20211217164918542141', 'Anja An', 'irawanpapang@gmail.com', '+62838323241213', 'EMAIL', NULL);

SET FOREIGN_KEY_CHECKS = 1;
