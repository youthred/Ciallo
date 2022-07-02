package io.github.youthred.ciallo.service;

import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.pojo.Log;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author https://github.com/youthred
 */
public interface LogInterceptor {

    Log log(Log log, Ciallo ciallo, MethodInvocation invocation);
}
