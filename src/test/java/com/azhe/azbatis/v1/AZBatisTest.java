package com.azhe.azbatis.v1;

import com.azhe.azbatis.v1.mapper.Blog;

import java.sql.*;

public class AZBatisTest {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");  // 加载驱动
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mybatistest?serverTimezone=UTC&useSSL=false", "root", "1003");  // 获得连接
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select * from blog where bid = 1");

        Blog blog = new Blog();

        // 获取结果集
        while (rs.next()) {
            Integer bid = rs.getInt("bid");
            String name = rs.getString("name");
            Integer authorId = rs.getInt("author_id");
            blog.setAuthorId(authorId);
            blog.setBid(bid);
            blog.setName(name);
        }
        System.out.println(blog);
        rs.close();
        statement.close();
        connection.close();
    }
}
