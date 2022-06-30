package io.github.youthred.ciallo.aop;

import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.util.ContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author https://github.com/youthred
 */
@Slf4j
public class CialloInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Ciallo ciallo = methodInvocation.getMethod().getAnnotation(Ciallo.class);
        if (ciallo.requestMapping()) {
            String servletPath = ContextHolderUtil.getCurrentHttpServletRequest().getServletPath();
            log.info(servletPath);
        }
        return methodInvocation.proceed();
    }
}
