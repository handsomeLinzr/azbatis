package com.azhe.azbatis.v1;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mapper接口的代理类
 */
public class AZMapperProxy implements InvocationHandler{

    public AZMapperProxy(AZSqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    private AZSqlSession sqlSession;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {
            return method.invoke(this, args);
        }
        String statementId = method.getDeclaringClass().getName() + "." + method.getName();
        return sqlSession.selectOne(statementId, args[0]);
    }
}
