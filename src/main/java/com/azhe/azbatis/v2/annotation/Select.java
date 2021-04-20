package com.azhe.azbatis.v2.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Select {

    /**
     * 执行的sql
     * @return
     */
    String value();

}
