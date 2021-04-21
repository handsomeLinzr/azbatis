package com.azhe.azbatis.v2.mapper;

import com.azhe.azbatis.v2.annotation.Entity;
import com.azhe.azbatis.v2.annotation.Select;

import java.util.List;

@Entity(Blog.class)
public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    @Select("select * from blog where bid = ?")
    Blog selectBlogById(Integer bid);

}
