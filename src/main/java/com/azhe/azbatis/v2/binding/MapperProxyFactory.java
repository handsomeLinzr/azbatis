package com.azhe.azbatis.v2.binding;

import com.azhe.azbatis.v2.session.DefaultSqlSession;

import java.lang.reflect.Proxy;

/**
 * Description: mapper接口的代理工厂
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 4:19 下午
 * @since V1.0.0
 */
public class MapperProxyFactory<T> {

    private final Class<T> mapperInterface;

    public MapperProxyFactory(Class<T> clazz) {
        this.mapperInterface = clazz;
    }

    /**
     * 创建接口代理
     * @param <T>
     * @return
     */
    public <T> T newInstance(DefaultSqlSession sqlSession) {
        return (T) Proxy.newProxyInstance(
                mapperInterface.getClassLoader(),
                new Class[] {mapperInterface},
                new MapperProxy(sqlSession)
        );
    }
}
