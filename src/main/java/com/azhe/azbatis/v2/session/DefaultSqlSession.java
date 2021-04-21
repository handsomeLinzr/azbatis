package com.azhe.azbatis.v2.session;

import com.azhe.azbatis.v2.executor.Executor;

/**
 * Description: 会话
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 12:34 下午
 * @since V1.0.0
 */
public class DefaultSqlSession {
    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
        executor = configuration.newExecutor();
    }

    /**
     * 获取mapper代理
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

    public Configuration getConfiguration() {
        return this.configuration;
    }

    /**
     * 查询单条记录
     * @param statementId namespace+methodName
     * @param parameters 参数
     * @param clazz 返回类型
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object[] parameters, Class<T> clazz) {
        // 获得对应的sql语句
        String sql = getConfiguration().getMappedStatement(statementId);
        return executor.query(sql, parameters, clazz);
    }

}
