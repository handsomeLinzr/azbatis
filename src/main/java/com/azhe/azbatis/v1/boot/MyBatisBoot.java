package com.azhe.azbatis.v1.boot;

import com.azhe.azbatis.v1.AZConfiguration;
import com.azhe.azbatis.v1.AZExecutor;
import com.azhe.azbatis.v1.AZSqlSession;
import com.azhe.azbatis.v1.mapper.Blog;
import com.azhe.azbatis.v1.mapper.BlogMapper;

public class MyBatisBoot {

    public static void main(String[] args) {
        AZSqlSession sqlSession = new AZSqlSession(new AZConfiguration(), new AZExecutor());
        BlogMapper blogMapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = blogMapper.selectBlogById(1);
        System.out.println(blog);
    }

}
