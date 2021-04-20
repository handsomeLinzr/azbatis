package com.azhe.azbatis.v2.binding;

import com.azhe.azbatis.v2.session.DefaultSqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Description:
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 4:42 下午
 * @since V1.0.0
 */
public class MapperProxy implements InvocationHandler {

    private final DefaultSqlSession sqlSession;
    private final Class<?> clazz;

    public MapperProxy(DefaultSqlSession sqlSession, Class<?> clazz) {
        this.sqlSession = sqlSession;
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (Object.class.equals(method.getDeclaringClass())) {  // Object的方法
            return method.invoke(this, args);
        } else {
            String statementId = method.getDeclaringClass().getName() + "." + method.getName();
            if (sqlSession.getConfiguration().hasStatementId(statementId)) {
                return sqlSession.selectOne(statementId, args, clazz);
            } else {  // statementId没有记录的方法
                return method.invoke(this, args);
            }
        }
    }
}
