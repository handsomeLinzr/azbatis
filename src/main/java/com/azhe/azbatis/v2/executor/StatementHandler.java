package com.azhe.azbatis.v2.executor;

import com.azhe.azbatis.v2.parameter.ParameterHandler;
import com.azhe.azbatis.v2.session.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Description: 封装jdbc，用于操作数据库，4大对象之一
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 5:22 下午
 * @since V1.0.0
 */
public class StatementHandler {

    private ResultSetHandler resultSetHandler = new ResultSetHandler();  // 暂时不用

    public <T> T query(String sql, Object[] parameters) {

        Connection connection;
        PreparedStatement ps = null;
        try {
            connection = getConnection();
            ps = connection.prepareStatement(sql);
            ParameterHandler ph = new ParameterHandler(ps);
            ph.setParameters(parameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private Connection getConnection() {
        String driver = Configuration.PROPERTIES.getString("jdbc.driver");
        String url = Configuration.PROPERTIES.getString("jdbc.url");
        String username = Configuration.PROPERTIES.getString("jdbc.username");
        String password = Configuration.PROPERTIES.getString("jdbc.password");

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }
}
