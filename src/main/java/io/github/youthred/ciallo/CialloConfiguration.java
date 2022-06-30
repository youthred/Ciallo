package io.github.youthred.ciallo;

import cn.hutool.core.util.ClassUtil;
import io.github.youthred.ciallo.annotation.Ciallo;
import io.github.youthred.ciallo.aop.CialloInterceptor;
import io.github.youthred.ciallo.prop.CialloProp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author https://github.com/youthred
 */
@Configuration
@EnableConfigurationProperties(CialloProp.class)
@ConditionalOnProperty(value = "ciallo.enable", havingValue = "true")
@Slf4j
public class CialloConfiguration {

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
