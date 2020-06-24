/*
 Navicat Premium Data Transfer

 Source Server         : 本机3306
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : javazy

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 24/06/2020 09:55:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '标题',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `content` text COLLATE utf8mb4_general_ci COMMENT '内容',
  `watch_count` int(32) DEFAULT '0' COMMENT '观看数量',
  `created_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '创建人姓名',
  `reply_count` int(32) DEFAULT '0' COMMENT '回复数量',
  `user_id` int(32) NOT NULL DEFAULT '0' COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of article
-- ----------------------------
BEGIN;
INSERT INTO `article` VALUES (1, '123', '2020-06-02 17:55:13', '2020-06-02 17:55:31', '3', 1, '1', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for article_label
-- ----------------------------
DROP TABLE IF EXISTS `article_label`;
CREATE TABLE `article_label` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `article_id` int(32) DEFAULT '0' COMMENT 'article主键',
  `label_id` int(32) DEFAULT '0' COMMENT 'label主键',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `label_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '分类名',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for log_operation
-- ----------------------------
DROP TABLE IF EXISTS `log_operation`;
CREATE TABLE `log_operation` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(32) COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '操作类型',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `content` varchar(512) COLLATE utf8mb4_general_ci NOT NULL COMMENT '操作内容',
  `ip` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT 'ip信息',
  `user_id` int(32) DEFAULT '0' COMMENT '用户id',
  `nick_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '用户昵称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_name` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `updated_at` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `permission_permission_name_uindex` (`permission_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='权限表';

-- ----------------------------
-- Records of permission
-- ----------------------------
BEGIN;
INSERT INTO `permission` VALUES (1, 'user:add', '2020-06-01 11:40:10', '2020-06-01 11:40:15');
INSERT INTO `permission` VALUES (2, 'user:edit', '2020-06-01 13:46:50', '2020-06-01 13:46:54');
INSERT INTO `permission` VALUES (3, 'user:edit:1', '2020-06-01 13:47:10', '2020-06-01 13:47:14');
COMMIT;

-- ----------------------------
-- Table structure for reply
-- ----------------------------
DROP TABLE IF EXISTS `reply`;
CREATE TABLE `reply` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `content` varchar(512) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '内容',
  `created_at` datetime NOT NULL COMMENT '创建时间',
  `article_id` int(32) NOT NULL COMMENT 'article主键',
  `reply_id` int(32) DEFAULT '0' COMMENT '回复主键',
  `user_id` int(32) DEFAULT '0' COMMENT '用户id',
  `nick_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '昵称',
  `phone` varchar(32) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号',
  `email` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增',
  `role_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '角色名',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `role_role_name_uindex` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (1, 'admin', '2020-06-01 11:39:33', '2020-06-01 11:39:38');
COMMIT;

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  `permission_id` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='角色权限关联表';

-- ----------------------------
-- Records of role_permission
-- ----------------------------
BEGIN;
INSERT INTO `role_permission` VALUES (2, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(64) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `phone` varchar(32) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '手机号',
  `email` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '邮箱',
  `salt` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '加密盐',
  `nick_name` varchar(64) COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '昵称',
  `status` int(4) DEFAULT NULL COMMENT '状态 0正常 -1异常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'zhang', 'AD99E85C188E2A3F8091DB106E079AB1', '2020-06-01 10:55:55', '2020-06-01 10:56:00', '17620080111', '912305258@qq.com', '1fd8c9a87dcd4d629a15f216e1bceae8', 'zhang', 0);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `role_id` int(11) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='用户角色关联';

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (1, 1, 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
