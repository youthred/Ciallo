package io.github.youthred.ciallo.aop;

import cn.hutool.core.util.IdUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.common.Constant;
import io.github.youthred.ciallo.entity.Ciallog;
import io.github.youthred.ciallo.properties.CialloProperty;
import io.github.youthred.ciallo.service.CiallogInterceptor;
import io.github.youthred.ciallo.service.CiallogSaver;
import io.github.youthred.ciallo.util.ContextHolderUtil;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author https://github.com/youthred
 */
@Slf4j
@Component
public class CialloMethodInterceptor implements MethodInterceptor {

    public static final ParameterNameDiscoverer PND = new DefaultParameterNameDiscoverer();

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start();
        Object proceed = invocation.proceed();
        sw.stop();

        Method method = invocation.getMethod();
        Ciallo ciallo = method.getDeclaredAnnotation(Ciallo.class);
        Ciallog ciallog = new Ciallog()
                .setId(IdUtil.getSnowflakeNextId())
                .setLogger(method.getDeclaringClass().getName() + "#" + method.getName())
                .setName(ciallo.value())
                .setLogTime(System.currentTimeMillis())
                .setTimeTake(sw.getLastTaskTimeMillis())
                .setServlet(ciallo.servlet())
                .setReq(this.parseReqJsonStr(method.getParameterTypes(), PND.getParameterNames(method), invocation.getArguments()))
                .setRes(JSONUtil.toJsonStr(proceed));
        if (ciallo.servlet()) {
            HttpServletRequest request = ContextHolderUtil.getCurrentHttpServletRequest();
            if (Objects.nonNull(request)) {
                String realIpHeader = SpringUtil.getBean(CialloProperty.class).getRealIpHeader();
                String ip;
                if (StringUtils.isNotBlank(realIpHeader)) {
                    ip = ServletUtil.getClientIPByHeader(request, realIpHeader);
                } else {
                    ip = ServletUtil.getClientIP(request);
                }
                ciallog.setIp(ip)
                        .setRequestMethod(request.getMethod())
                        .setServletPath(request.getServletPath());
                String servletPath = request.getServletPath();
                log.info(servletPath);
            } else {
                log.warn(Constant.LOG_NAME_HEAD + "Current 'HttpServletRequest' is null");
            }
        }
        Object o = SpringUtil.getBean(CiallogInterceptor.class).ciallog(
                ciallog,
                ciallo,
                invocation
        );
        SpringUtil.getBean(CiallogSaver.class).save(o);
        return proceed;
    }

    private String parseReqJsonStr(Class<?>[] parameterTypes, String[] parameterNames, Object[] parameterArgs) {
        Map<String, Object> map = new HashMap<>();
        for (int i = 0; i < parameterTypes.length; i++) {
            map.put(parameterTypes[i] + " " + parameterNames[i], parameterArgs[i]);
        }
        return JSONUtil.toJsonStr(map);
    }
}
