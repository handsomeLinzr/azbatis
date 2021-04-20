package com.azhe.azbatis.v2.annotation;

import java.lang.annotation.*;

/**
 * 实体类注解，用于标记接口的返回对象
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Entity {

    /**
     * 返回的pojo类
     * @return
     */
    Class<?> value();

}
