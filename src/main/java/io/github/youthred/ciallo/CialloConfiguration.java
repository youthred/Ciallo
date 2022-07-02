package io.github.youthred.ciallo;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.db.ds.DSFactory;
import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.aop.CialloMethodInterceptor;
import io.github.youthred.ciallo.common.Constant;
import io.github.youthred.ciallo.properties.CialloProperty;
import io.github.youthred.ciallo.service.CiallogInterceptor;
import io.github.youthred.ciallo.service.CiallogSaver;
import io.github.youthred.ciallo.service.impl.CiallogInterceptorImpl;
import io.github.youthred.ciallo.service.impl.CiallogSaverImpl;
import lombok.extern.slf4j.Slf4j;
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
public class CialloConfiguration {

    private final CialloProperty cialloProperty;

    public CialloConfiguration(CialloProperty cialloProperty) {
        this.cialloProperty = cialloProperty;
        init();
    }

    public void init() {
        if (cialloProperty.getSaveLog()) {
            if (Objects.isNull(cialloProperty.getDs())) {
                log.error(Constant.LOG_NAME_HEAD + "Properties 'ciallo.db' is null");
                return;
            }
            DSFactory.setCurrentDSFactory(DSFactory.create(cialloProperty.getDs().getSetting()));
        }
    }

    @Bean
    public DefaultPointcutAdvisor cialloPointcutAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("@annotation(" + ClassUtil.getClassName(Ciallo.class, false) + ")");
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(new CialloMethodInterceptor());
        log.info(Constant.LOG_NAME_HEAD + "CialloAop loaded.");
        return advisor;
    }

    @Bean
    @ConditionalOnMissingBean
    public CiallogInterceptor ciallogInterceptor() {
        return new CiallogInterceptorImpl();
    }

    @Bean
    @ConditionalOnMissingBean
    public CiallogSaver logSaver() {
        return new CiallogSaverImpl();
    }
}
