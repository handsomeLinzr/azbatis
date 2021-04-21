package com.azhe.azbatis.v2.plugin;

import com.azhe.azbatis.v2.annotation.Intercepts;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 插件拦截器的代理类
 */
public class Plugin implements InvocationHandler {

    // 被代理对象
    private final Object target;
    // 当前的拦截器
    private final Interceptor interceptor;

    public Plugin(Object target, Interceptor interceptor) {
        this.target = target;
        this.interceptor = interceptor;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (interceptor.getClass().isAnnotationPresent(Intercepts.class)) {  // 有加注解
            if (method.getName().endsWith(interceptor.getClass().getAnnotation(Intercepts.class).method())) {  // 方法匹配上
                return interceptor.intercept(new Invocation(this.target, method, args));
            }
        }
        // 跳过拦截，直接执行
        return method.invoke(target, args);
    }

    /**
     * 创建并放回代理对象
     * @param target
     * @param interceptor
     * @return
     */
    public static Object wrap(Object target, Interceptor interceptor) {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                new Plugin(target, interceptor)
        );
    }

}
