package com.azhe.azbatis.v2.cache;

/**
 * Description: 缓存key
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/21 12:27 下午
 * @since V1.0.0
 */
public class CacheKey {
    private static final int DEFAULT_HASHCODE = 17;   // 默认哈希值
    private static final int DEFAULT_MULTIPLIER = 37;  // 默认倍数

    private int hashcode;
    private int count;
    private int multiplier;

    /**
     * 初始化
     */
    public CacheKey() {
        hashcode = DEFAULT_HASHCODE;
        count = 0;
        multiplier = DEFAULT_MULTIPLIER;
    }

    /**
     * 获得缓存的code值
     * @return
     */
    public int getCode() {
        return hashcode;
    }

    /**
     * 自定义设计hashcode的方法，确保同一个sql + 参数 对应同一个hashcode
     * @param object
     */
    public void update(Object object) {
        int baseCode = object == null ? 1 : object.hashCode();
        count ++;
        hashcode *= count;
        hashcode = hashcode * multiplier + baseCode;
    }
}