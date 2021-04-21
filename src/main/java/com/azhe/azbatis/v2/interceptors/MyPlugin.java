package com.azhe.azbatis.v2.interceptors;

import com.azhe.azbatis.v2.annotation.Intercepts;
import com.azhe.azbatis.v2.plugin.Interceptor;
import com.azhe.azbatis.v2.plugin.Invocation;

import java.util.Arrays;

@Intercepts(method = "query")
public class MyPlugin implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        String sql = (String) invocation.getArgs()[0];
        Object[] parameters = (Object[]) invocation.getArgs()[1];
        Class<?> pojoClass = (Class<?>) invocation.getArgs()[2];
        System.out.println(sql);
        System.out.println(Arrays.toString(parameters));
        System.out.println(pojoClass);
        return invocation.proceed();
    }
}
