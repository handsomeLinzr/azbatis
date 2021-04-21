package com.azhe.azbatis.v2.plugin;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description: 用于代理被来接对象的代理类，同时提供创建代理类的方法
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/21 6:02 下午
 * @since V1.0.0
 */
public class Plugin implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }

    /**
     * 创建被拦截的对象
     * @return
     */
    public static Object wrap(Object target) {
        return null;
    }

}
