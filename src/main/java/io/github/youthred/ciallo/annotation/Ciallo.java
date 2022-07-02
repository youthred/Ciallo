package io.github.youthred.ciallo.annotation;

import java.lang.annotation.*;

/**
 * @author https://github.com/youthred
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ciallo {

    /**
     * 方法名称
     * @return String
     */
    String value() default "";

    /**
     * 是否属于HTTP接口调用
     *
     * @return 默认true
     */
    boolean servlet() default true;
}
