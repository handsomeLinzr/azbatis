package com.azhe.azbatis.v2.plugin;

/**
 * 插件接口
 */
public interface Interceptor {

    /**
     * 拦截国际
     * @return
     */
    Object intercept(Invocation invocation) throws Throwable;

    /**
     * 获得当前拦截后的代理对象
     * @param target
     * @return
     */
    default Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

}
