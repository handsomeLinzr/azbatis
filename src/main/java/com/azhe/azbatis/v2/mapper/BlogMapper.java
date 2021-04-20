package com.azhe.azbatis.v2.mapper;

public interface BlogMapper {
    /**
     * 根据主键查询文章
     * @param bid
     * @return
     */
    Blog selectBlogById(Integer bid);

}
