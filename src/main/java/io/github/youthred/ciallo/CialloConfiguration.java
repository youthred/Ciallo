package io.github.youthred.ciallo;

import cn.hutool.core.util.ClassUtil;
import cn.hutool.db.ds.DSFactory;
import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.aop.CialloInterceptor;
import io.github.youthred.ciallo.properties.CialloProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
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
@ConditionalOnProperty(value = "ciallo.enable", havingValue = "true")
@Slf4j
@Import(cn.hutool.extra.spring.SpringUtil.class)
@RequiredArgsConstructor
public class CialloConfiguration {

    private final CialloProperty cialloProperty;

    @Bean
    public void init() {
        if (Objects.isNull(cialloProperty.getDb())) {
            log.error("[Ciallo] Properties 'ciallo.db' is null");
            return;
        }
        DSFactory.setCurrentDSFactory(DSFactory.create(cialloProperty.getDb().getSetting()));
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
}
