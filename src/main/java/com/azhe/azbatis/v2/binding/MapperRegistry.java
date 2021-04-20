package com.azhe.azbatis.v2.binding;

import com.azhe.azbatis.v2.session.DefaultSqlSession;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 用于维护mapper和对应的mapper代理工厂的关系
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 4:18 下午
 * @since V1.0.0
 */
public class MapperRegistry {

    private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap<>();  //存放mapper接口和mapper代理工厂的关系

    /**
     * 添加 mapper接口和对应的代理工厂的关系
     */
    public void addMapper(Class<?> clazz) {
        knownMappers.put(clazz, new MapperProxyFactory<>(clazz));
    }

    /**
     * 创建并返回mapper接口代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        MapperProxyFactory<?> mapperProxyFactory = knownMappers.get(clazz);
        if (mapperProxyFactory == null) {
            throw new RuntimeException("Type: " + clazz + "can not find");
        }
        return mapperProxyFactory.newInstance(sqlSession);
    }
}
