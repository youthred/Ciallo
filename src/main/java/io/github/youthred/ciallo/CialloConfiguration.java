package io.github.youthred.ciallo;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.db.ds.DSFactory;
import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.aop.CialloInterceptor;
import io.github.youthred.ciallo.entity.Ciallog;
import io.github.youthred.ciallo.properties.CialloProperty;
import io.github.youthred.ciallo.service.CiallogInterceptor;
import io.github.youthred.ciallo.service.CiallogSaver;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Objects;

/**
 * @author https://github.com/youthred
 */
@Configuration
@EnableConfigurationProperties(CialloProperty.class)
@ConditionalOnProperty(value = "ciallo.enable", havingValue = "true", matchIfMissing = true)
@Slf4j
@Import(cn.hutool.extra.spring.SpringUtil.class)
@RequiredArgsConstructor
public class CialloConfiguration {

    private final CialloProperty cialloProperty;

    @Bean
    public void init() {
        if (Objects.isNull(cialloProperty.getDs())) {
            log.error("[Ciallo] Properties 'ciallo.db' is null");
            return;
        }
        DSFactory.setCurrentDSFactory(DSFactory.create(cialloProperty.getDs().getSetting()));
    }

    @Bean
    public DefaultPointcutAdvisor cialloPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(" + ClassUtil.getClassName(Ciallo.class, false) + ")");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new CialloInterceptor());
        log.info("[Ciallo] CialloAop loaded.");
        return advisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public CiallogInterceptor logInterceptor() {
        return (ciallog, ciallo, invocation) -> ciallog;
    }

    @Bean
    @ConditionalOnMissingBean
    public CiallogSaver logSaver() {
        return o -> {
            if (o instanceof Ciallog) {
                try {
                    Ciallog ciallog = (Ciallog) o;
                    log.info("[Ciallo] SAVED");
                } catch (Exception e) {
                    log.error(ExceptionUtils.getStackTrace(e));
                }
            } else {
                log.warn("[Ciallo] You implemented and Spring componentized your own 'CiallogInterceptor' and returned an object of another type, the default 'CiallogSaver' cannot handle objects other than 'io.github.youthred.ciallo.entity.Ciallog', please implement and Spring componentized' CiallogSaver' to custom handle logs.");
            }
        };
    }
}
