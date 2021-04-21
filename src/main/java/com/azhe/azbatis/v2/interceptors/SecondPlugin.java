package com.azhe.azbatis.v2.interceptors;

import com.azhe.azbatis.v2.annotation.Intercepts;
import com.azhe.azbatis.v2.plugin.Interceptor;
import com.azhe.azbatis.v2.plugin.Invocation;
import com.azhe.azbatis.v2.plugin.Plugin;

import java.lang.reflect.Method;

@Intercepts(method = "query")
public class SecondPlugin implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Method method = invocation.getMethod();
        System.out.println(method);
        String sql = (String) invocation.getArgs()[0];
        System.out.println("second plugin：" + sql);
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }
}
