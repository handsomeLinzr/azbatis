package com.azhe.azbatis.v2.executor;

/**
 * Description: 执行器，4大对象之一
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 12:42 下午
 * @since V1.0.0
 */
public interface Executor {

    <T> T query(String sql, Object[] parameters);

}
