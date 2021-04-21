package com.azhe.azbatis.v2.executor;

import com.azhe.azbatis.v2.cache.CacheKey;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 带缓存的执行器
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 4:30 下午
 * @since V1.0.0
 */
public class CacheExecutor implements Executor{

    private Executor delegate;
    private Map<Integer, Object> cache = new HashMap<>();  // 缓存

    public CacheExecutor(Executor executor) {
        this.delegate = executor;
    }

    @Override
    public <T> T query(String sql, Object[] parameters, Class<T> clazz) {
        CacheKey cacheKey = new CacheKey();
        cacheKey.update(sql);
        cacheKey.update(joinStr(parameters));
        if (cache.containsKey(cacheKey.getCode())) {
            return (T) cache.get(cacheKey.getCode());
        }
        T result = this.delegate.query(sql, parameters, clazz);
        cache.put(cacheKey.getCode(), result);
        return result;
    }

    /**
     * 将 Object 对象数组转为String，同一个字符串的hashcode是一样的，但是同样值的不同对象的hashcode是不一样的
     * @param objects
     * @return
     */
    private String joinStr(Object[] objects) {
        StringBuilder sb = new StringBuilder();
        if (objects != null && objects.length > 0) {
            for (Object object : objects) {
                sb.append(object).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
