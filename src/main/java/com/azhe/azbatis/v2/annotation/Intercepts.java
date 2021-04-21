package com.azhe.azbatis.v2.annotation;

import java.lang.annotation.*;

/**
 * Description: 用于标注在插件上，指定插件所要拦截的方法
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/21 6:08 下午
 * @since V1.0.0
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Intercepts {

    /**
     * 拦截的方法
     * @return
     */
    String method();
}
