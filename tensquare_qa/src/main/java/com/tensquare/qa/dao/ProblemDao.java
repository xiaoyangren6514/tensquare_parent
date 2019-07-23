package com.tensquare.qa.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.qa.pojo.Problem;
import org.springframework.data.jpa.repository.Query;

/**
 * problem数据访问接口
 *
 * @author Administrator
 */
public interface ProblemDao extends JpaRepository<Problem, String>, JpaSpecificationExecutor<Problem> {
    /**
     * 根据标签ID查询最新问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select p from Problem p where id in(select problemid from PL where labelid = ?1) order by replytime desc")
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);


    /**
     * 根据标签ID查询热门问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select p from Problem p where id in(select problemid from PL where labelid = ?1) order by reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签ID查询等待回答问题列表
     *
     * @param labelId
     * @param pageable
     * @return
     */
    @Query(value = "select p from Problem p where id in(select problemid from PL where labelid = ?1) and reply =0 order by createtime desc")
    public Page<Problem> findWaitReplyListByLabelId(String labelId, Pageable pageable);
}
