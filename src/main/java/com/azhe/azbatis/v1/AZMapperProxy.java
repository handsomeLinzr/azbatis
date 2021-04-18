package com.azhe.azbatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mapper接口的代理类
 */
public class AZMapperProxy implements InvocationHandler {

    private final AZSqlSession sqlSession;

    // 从构造函数传递进来
    public AZMapperProxy(AZSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String statementId = method.getDeclaringClass().getName() + "." + method.getName();  // 获得namespace+id
        return sqlSession.selectOne(statementId, args[0]);
    }
}
