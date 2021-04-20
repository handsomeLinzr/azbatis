package com.azhe.azbatis.v2.executor;

/**
 * Description: 默认执行器
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 1:36 下午
 * @since V1.0.0
 */
public class SimpleExecutor implements Executor {

    @Override
    public <T> T query(String sql, Object[] parameters) {

        // 创建一个执行处理器，并调用
        StatementHandler statementHandler = new StatementHandler();
        return statementHandler.query(sql, parameters);
    }
}
