package com.azhe.azbatis.v2.executor;

/**
 * Description: 带缓存的执行器
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 4:30 下午
 * @since V1.0.0
 */
public class CacheExecutor implements Executor{

    private Executor executor;

    public CacheExecutor(Executor executor) {
        this.executor = executor;
    }

    @Override
    public <T> T query(String sql, Object[] parameters, Class<T> clazz) {
        return null;
    }
}
