package io.github.youthred.ciallo.aop;

import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.pojo.Log;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author https://github.com/youthred
 */
public interface LogInterceptor {

    default Log log(Log log, Ciallo ciallo, MethodInvocation invocation) {
        return log;
    }
}
