package com.azhe.azbatis.v2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 插件拦截器链，用于保存所有注册的插件拦截器
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/21 6:13 下午
 * @since V1.0.0
 */
public class InterceptorChain {

    /**
     * 拦截器链
     */
    private final List<Interceptor> chain = new ArrayList<>();

    /**
     * 添加拦截器
     * @param interceptor
     */
    public void addInterceptor(Interceptor interceptor) {
        chain.add(interceptor);
    }

}
