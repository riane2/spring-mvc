package com.riane.apilimitrate.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 自定义注解：每秒放入桶中的token
 */
@Inherited
@Target(ElementType.METHOD)
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LimitRate {

    @AliasFor("value")
    double limitNum() default 10;

    @AliasFor("limitNum")
    double value() default 10;
}
