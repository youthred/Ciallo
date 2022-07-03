package io.github.youthred.ciallo.common;

import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.properties.CialloProperty;

public class Constant {

    public static final String LOG_NAME = "[Ciallo]";
    public static final String LOG_NAME_HEAD = "[Ciallo] ";

    public static final String DEFAULT_TABLE_CREATE_SQL_MYSQL =
            "CREATE TABLE `" + SpringUtil.getBean(CialloProperty.class).getCiallogTableName() + "` (\n" +
                    "  `id` bigint NOT NULL,\n" +
                    "  `logger` varchar(128) DEFAULT NULL COMMENT '完全限定名',\n" +
                    "  `log_time` datetime DEFAULT NULL COMMENT '触发时间',\n" +
                    "  `time_take` bigint DEFAULT NULL COMMENT '耗时 millis',\n" +
                    "  `servlet` bit(1) DEFAULT NULL COMMENT '是否Http接口 1是 0否',\n" +
                    "  `ip` varchar(15) DEFAULT NULL COMMENT '请求IP',\n" +
                    "  `request_method` varchar(6) DEFAULT NULL COMMENT '请求类型',\n" +
                    "  `servlet_path` varchar(255) DEFAULT NULL COMMENT 'URI',\n" +
                    "  `req` varchar(2048) DEFAULT NULL COMMENT '入参',\n" +
                    "  `res` varchar(2048) DEFAULT NULL COMMENT '出参',\n" +
                    "  PRIMARY KEY (`id`) USING BTREE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8";
    public static final String DEFAULT_TABLE_CREATE_SQL_ORACLE =
            "CREATE TABLE `" + SpringUtil.getBean(CialloProperty.class).getCiallogTableName() + "`\n" +
                    "(\n" +
                    "    `id`             number primary key,\n" +
                    "    `logger`         varchar2(128),\n" +
                    "    `log_time`       data,\n" +
                    "    `time_take`      number,\n" +
                    "    `servlet`        byte(1),\n" +
                    "    `ip`             varchar2(15),\n" +
                    "    `request_method` varchar2(6),\n" +
                    "    `servlet_path`   varchar2(255),\n" +
                    "    `req`            varchar2(2048),\n" +
                    "    `res`            varchar2(2048)\n" +
                    ")";
}
