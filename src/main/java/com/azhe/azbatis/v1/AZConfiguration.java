package com.azhe.azbatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 配置文件
 */
public class AZConfiguration {

    private static final ResourceBundle RESOURCE_BUNDLE;
    static {
        // 静态代码块的代码仅在类加载的时候执行一次，避免多次执行
        RESOURCE_BUNDLE = ResourceBundle.getBundle("v1sql");
    }

    /**
     * 获得sql配置
     * @return
     */
    public ResourceBundle getResourceBundle() {
        return RESOURCE_BUNDLE;
    }

    /**
     * 获得mapper代理
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, AZSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new AZMapperProxy(sqlSession)
        );
    }

}
