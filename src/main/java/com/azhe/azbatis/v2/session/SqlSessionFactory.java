package com.azhe.azbatis.v2.session;

/**
 * Description: 解析配置文件，开启sqlSession会话，单例模式
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 12:33 下午
 * @since V1.0.0
 */
public class SqlSessionFactory {

    private Configuration configuration;
    private static SqlSessionFactory SQL_SESSION_FACTORY = null;

    private SqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    /**
     * 创建会话工厂，同时解析配置文件，只解析一次
     * @return
     */
    public static SqlSessionFactory build() {
        if (SQL_SESSION_FACTORY == null) {
            SQL_SESSION_FACTORY = new SqlSessionFactory(new Configuration());
        }
        return SQL_SESSION_FACTORY;
    }

    /**
     * 开启会话
     * @return
     */
    public DefaultSqlSession openSession() {
        return new DefaultSqlSession(configuration);
    }

}
