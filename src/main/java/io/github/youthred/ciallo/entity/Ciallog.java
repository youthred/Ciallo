package io.github.youthred.ciallo.entity;

import cn.hutool.core.util.IdUtil;
import cn.hutool.db.Entity;
import cn.hutool.extra.spring.SpringUtil;
import io.github.youthred.ciallo.properties.CialloProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
public class Ciallog {

    private Long id;

    /**
     * 完全限定名
     */
    private String logger;

    /**
     * 触发时间
     */
    public LocalDateTime logTime;

    /**
     * 耗时 millis
     */
    public Long timeTake;

    /**
     * 是否Http接口
     */
    private boolean servlet;

    /**
     * 请求IP
     */
    private String ip;

    /**
     * 请求类型
     */
    private String requestMethod;

    /**
     * URI
     */
    private String servletPath;

    /**
     * 入参
     */
    private String req;

    /**
     * 出参
     */
    private String res;

    public Entity toEntity() {
        return Entity.create(SpringUtil.getBean(CialloProperty.class).getLogTableName())
                .set("id", IdUtil.getSnowflakeNextId())
                .set("logger", this.logger)
                .set("log_time", new Timestamp(System.currentTimeMillis()))
                .set("time_take", this.timeTake)
                .set("servlet", this.servlet)
                .set("ip", this.ip)
                .set("request_method", this.requestMethod)
                .set("servlet_path", this.servletPath)
                .set("req", this.req)
                .set("res", this.res);
    }
}
