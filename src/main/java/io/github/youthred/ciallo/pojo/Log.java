package io.github.youthred.ciallo.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author https://github.com/youthred
 */
@Data
@Accessors(chain = true)
public class Log {

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
}
