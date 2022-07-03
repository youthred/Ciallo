CREATE TABLE `t_ciallog`
(
    `id`             number primary key,
    `logger`         varchar2(128),
    `name`           varchar2(255),
    `log_time`       data,
    `time_take`      number,
    `servlet`        byte(1),
    `ip`             varchar2(15),
    `request_method` varchar2(6),
    `servlet_path`   varchar2(255),
    `req`            varchar2(2048),
    `res`            varchar2(2048)
);

COMMENT on `t_ciallog`.`id` is '完全限定名';
COMMENT on `t_ciallog`.`logger` is '触发时间';
COMMENT on `t_ciallog`.`name` is '方法名称';
COMMENT on `t_ciallog`.`log_time` is '耗时 millis';
COMMENT on `t_ciallog`.`time_take` is '是否Http接口 1是 0否';
COMMENT on `t_ciallog`.`servlet` is '请求IP';
COMMENT on `t_ciallog`.`ip` is '请求类型';
COMMENT on `t_ciallog`.`request_method` is 'URI';
COMMENT on `t_ciallog`.`servlet_path` is '入参';
COMMENT on `t_ciallog`.`req` is '出参';