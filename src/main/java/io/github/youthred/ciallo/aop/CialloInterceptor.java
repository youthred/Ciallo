package io.github.youthred.ciallo.aop;

import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.service.DbService;
import io.github.youthred.ciallo.util.ContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author https://github.com/youthred
 */
@Slf4j
@Component
public class CialloInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        Ciallo ciallo = methodInvocation.getMethod().getAnnotation(Ciallo.class);
        if (ciallo.servlet()) {
            HttpServletRequest request = ContextHolderUtil.getCurrentHttpServletRequest();
            String servletPath = request.getServletPath();
            log.info(servletPath);
        }
        DbService.check();
        return methodInvocation.proceed();
    }
}
