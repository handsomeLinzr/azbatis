package com.azhe.azbatis.v2.boot;

import com.azhe.azbatis.v2.mapper.Blog;
import com.azhe.azbatis.v2.mapper.BlogMapper;
import com.azhe.azbatis.v2.session.DefaultSqlSession;
import com.azhe.azbatis.v2.session.SqlSessionFactory;

/**
 * Description:
 *
 * @author Linzr
 * @version V1.0.0
 * @date 2021/4/20 12:32 下午
 * @since V1.0.0
 */
public class MyBatisBoot {

    public static void main(String[] args) {
        SqlSessionFactory sqlSessionFactory = SqlSessionFactory.build();
        DefaultSqlSession sqlSession = sqlSessionFactory.openSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = mapper.selectBlogById(1);
        System.out.println(mapper);
        System.out.println(blog);
    }

}
