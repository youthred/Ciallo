package io.github.youthred.ciallo.service;

import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.entity.Ciallog;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author https://github.com/youthred
 */
public interface CiallogInterceptor {

    /**
     * 构造实体
     * <p>
     * 返回默认实体
     * 若要自定义返回体请实现 CiallogInterceptor.ciallog 并注册为Spring组件
     *
     * @param ciallog    默认实体
     * @param ciallo     Ciallo注解
     * @param invocation MethodInvocation
     * @return Ciallog
     */
    Object ciallog(Ciallog ciallog, Ciallo ciallo, MethodInvocation invocation);
}
