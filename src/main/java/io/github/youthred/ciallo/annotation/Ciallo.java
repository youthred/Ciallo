package io.github.youthred.ciallo.annotation;

import java.lang.annotation.*;

/**
 * @author https://github.com/youthred
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Ciallo {

    String value() default "";
    boolean requestMapping() default true;
}
