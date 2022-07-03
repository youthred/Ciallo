CREATE TABLE `t_ciallog`
(
    `id`             bigint NOT NULL,
    `logger`         varchar(128)  DEFAULT NULL COMMENT '完全限定名',
    `name`           varchar(255)  DEFAULT NULL COMMENT '方法名称',
    `log_time`       datetime      DEFAULT NULL COMMENT '触发时间',
    `time_take`      bigint        DEFAULT NULL COMMENT '耗时 millis',
    `servlet`        bit(1)        DEFAULT NULL COMMENT '是否Http接口 1是 0否',
    `ip`             varchar(15)   DEFAULT NULL COMMENT '请求IP',
    `request_method` varchar(6)    DEFAULT NULL COMMENT '请求类型',
    `servlet_path`   varchar(255)  DEFAULT NULL COMMENT 'URI',
    `req`            varchar(2048) DEFAULT NULL COMMENT '入参',
    `res`            varchar(2048) DEFAULT NULL COMMENT '出参',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into