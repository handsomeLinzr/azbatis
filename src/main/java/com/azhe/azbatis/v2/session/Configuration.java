package com.azhe.azbatis.v2.session;

import com.azhe.azbatis.v2.binding.MapperRegistry;
import com.azhe.azbatis.v2.executor.CacheExecutor;
import com.azhe.azbatis.v2.executor.Executor;
import com.azhe.azbatis.v2.executor.SimpleExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Description: 获取并记录全局配置
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 12:35 下午
 * @since V1.0.0
 */
public class Configuration {

    public static final ResourceBundle SQL_MAPPINGS;  // sql全局映射关系配置
    public static final ResourceBundle PROPERTIES;    // 全局配置

    private Map<String, String> mapperStatements = new HashMap<>();  // 用于保存statementId 和 sql的关系
    private MapperRegistry mapperRegistry = new MapperRegistry();  // 保存mapper和代理工厂的关系

    static {
        SQL_MAPPINGS = ResourceBundle.getBundle("sql");
        PROPERTIES = ResourceBundle.getBundle("azbatis");
    }

    /**
     * 构造函数初始化时，解析全局配置文件
     */
    public Configuration() {
        // Note：在properties和注解中重复配置SQL会覆盖
        // 1.解析sql
        for (String key : SQL_MAPPINGS.keySet()) {  // 解析
            String value = SQL_MAPPINGS.getString(key);
            String sql = value.split("--")[0];  // sql语句
            String pojoStr = value.split("--")[1];  // 返回对象
            String mapperClassStr = key.substring(0, key.lastIndexOf("."));  // mapper对象
            Class<?> clazz = null;  // mapper接口
            try {
                clazz = Class.forName(mapperClassStr);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            mapperRegistry.addMapper(clazz);
            mapperStatements.put(key, sql);
        }
    }

    /**
     * 创建一个执行器
     * @return
     */
    public Executor newExecutor() {
        Executor executor = null;
        if (PROPERTIES.containsKey("cache.enabled") && PROPERTIES.getString("cache.enabled").equals("true")) {
            executor = new CacheExecutor(new SimpleExecutor());
        } else {
            executor = new SimpleExecutor();
        }
        return executor;
    }

    /**
     * 通过statementId获得sql
     * @param statementId
     * @return
     */
    public String getMappedStatement(String statementId) {
        return mapperStatements.get(statementId);
    }

    /**
     * 判断是否存在statementId
     * @param statementId
     * @return
     */
    public boolean hasStatementId(String statementId) {
        return mapperStatements.containsKey(statementId);
    }

    /**
     * 获得mapper接口代理
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<T> clazz, DefaultSqlSession sqlSession) {
        return mapperRegistry.getMapper(clazz, sqlSession);
    }
}
