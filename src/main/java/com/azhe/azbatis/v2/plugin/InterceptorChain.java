package com.azhe.azbatis.v2.plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器链，维护一个拦截器的list
 */
public class InterceptorChain {

    private final List<Interceptor> chain = new ArrayList<>();

    /**
     * 添加拦截器
     * @param interceptor
     */
    public void addInterceptor(Interceptor interceptor) {
        this.chain.add(interceptor);
    }

    public boolean hasInterceptors() {
        return this.chain.size() > 0;
    }

    /**
     * 对拦截对象进行层层代理拦截
     * @param target
     * @return
     */
    public Object pluginAll(Object target) {
        for (Interceptor interceptor : chain) {
            target = interceptor.plugin(target);
        }
        return target;
    }

}
