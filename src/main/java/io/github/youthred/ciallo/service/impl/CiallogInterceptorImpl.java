package io.github.youthred.ciallo.service.impl;

import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.entity.Ciallog;
import io.github.youthred.ciallo.service.CiallogInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * 默认日志拦截构造实现
 *
 * @author https://github.com/youthred
 */
public class CiallogInterceptorImpl implements CiallogInterceptor {

    @Override
    public Object ciallog(Ciallog ciallog, Ciallo ciallo, MethodInvocation invocation) {
        return ciallog;
    }
}
