package com.azhe.azbatis.v1;

import java.lang.reflect.Proxy;
import java.util.ResourceBundle;

/**
 * 配置文件
 */
public class AZConfiguration {
    // sql 配置文件
    public static final ResourceBundle sqlBundle;
    static {
        sqlBundle = ResourceBundle.getBundle("v1sql");
    }

    /**
     * 获得MapperProxy代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, AZSqlSession sqlSession) {
        return (T)Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new AZMapperProxy(sqlSession));
    }
}
