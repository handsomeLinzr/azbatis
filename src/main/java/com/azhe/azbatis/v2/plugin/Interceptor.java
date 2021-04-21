package com.azhe.azbatis.v2.plugin;

/**
 * Description: 插件拦截器接口，自定义插件拦截器必须实现这个接口
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/21 5:57 下午
 * @since V1.0.0
 */
public interface Interceptor {

    /**
     * 插件的核心逻辑实现
     * @return
     */
    Object intercept();

    /**
     * 对被拦截的对象进行代理，返回代理对象
     * @param target
     * @return
     */
    default Object plugin(Object target) {
        return Plugin.wrap(target);
    }

}
