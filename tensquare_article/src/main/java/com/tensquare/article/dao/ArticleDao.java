package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * article数据访问接口
 *
 * @author Administrator
 */
public interface ArticleDao extends JpaRepository<Article, String>, JpaSpecificationExecutor<Article> {
    /**
     * 文章审核
     *
     * @param id
     */
    @Modifying
    @Query(value = "update Article set state = '1' where id = ?1")
    public void examine(String id);

    /**
     * 文章点赞
     *
     * @param id
     */
    @Modifying
    @Query(value = "update Article set thumbup = thumbup+1 where id = ?1")
    public void updateThumbup(String id);
}
