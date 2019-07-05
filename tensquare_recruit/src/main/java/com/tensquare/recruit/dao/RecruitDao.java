package com.tensquare.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.recruit.pojo.Recruit;

import java.util.List;

/**
 * recruit数据访问接口
 *
 * @author Administrator
 */
public interface RecruitDao extends JpaRepository<Recruit, String>, JpaSpecificationExecutor<Recruit> {

    /**
     * 根据职位状态查询前4条记录，并以创建日期降序排序
     *
     * @param state
     * @return
     */
    List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);// where state = ? order by createtime desc


    /**
     * 最新职位列表
     *
     * @param state
     * @return
     */
    List<Recruit> findByStateNotOrderByCreatetimeDesc(String state);// where state != ? order by createtime desc
}
