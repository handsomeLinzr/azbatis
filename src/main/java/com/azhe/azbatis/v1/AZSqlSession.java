package com.azhe.azbatis.v1;

public class AZSqlSession {

    private final AZConfiguration configuration;
    private final AZExecutor executor;  // 执行器

    public AZSqlSession(AZConfiguration configuration, AZExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 查询一条记录
     * @param statementId 对应的记录SQL的statementId
     * @param parameter 参数
     * @param <T> 返回值
     * @return
     */
    public <T> T selectOne(String statementId, Object parameter) {
        String sql = AZConfiguration.sqlBundle.getString(statementId);
        if ("".equals(sql)) {
            return null;
        }
        return executor.query(sql, parameter);
    }

    /**
     * 获得mapper的代理类
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

}
