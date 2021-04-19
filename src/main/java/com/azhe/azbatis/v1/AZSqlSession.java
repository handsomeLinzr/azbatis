package com.azhe.azbatis.v1;

public class AZSqlSession {

    private AZConfiguration configuration;
    private AZExecutor executor;

    public AZSqlSession(AZConfiguration configuration, AZExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    /**
     * 获得mapper接口代码
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz) {
        return configuration.getMapper(clazz, this);
    }

    /**
     * 查询数据
     * @param statementId
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object parameter) {
        String sql = configuration.getResourceBundle().getString(statementId);
        return executor.query(sql, parameter);
    }

}
