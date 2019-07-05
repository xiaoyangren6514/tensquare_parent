package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhonglq on 2019/6/25.
 */
@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询所有标签
     *
     * @return
     */
    public List<Label> findAll() {
        return labelDao.findAll();
    }

    /**
     * 根据标签ID查找标签
     *
     * @param id
     * @return
     */
    public Label findById(String id) {
        return labelDao.findById(id).get();
    }

    /**
     * 添加标签
     *
     * @param label
     */
    public void add(Label label) {
        label.setId(idWorker.nextId() + "");
        labelDao.save(label);
    }

    /**
     * 更新标签
     *
     * @param label
     */
    public void update(Label label) {
        labelDao.save(label);
    }

    /**
     * 根据id删除标签
     *
     * @param id
     */
    public void deleteById(String id) {
        labelDao.deleteById(id);
    }

    /**
     * 根据条件查询标签
     *
     * @param searchMap
     * @return
     */
    public List<Label> searchLabel(Map searchMap) {
        Specification<Label> specification = createSpecification(searchMap);
        return labelDao.findAll(specification);
    }

    /**
     * 根据参数构造查询条件
     *
     * @param serachMap
     * @return
     */
    private Specification<Label> createSpecification(Map serachMap) {
        return new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicateList = new ArrayList<>();
                // labelname校验
                String labelname = (String) serachMap.get("labelname");
                if (labelname != null && !"".equals(labelname)) {
                    predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class), "%" + labelname + "%"));
                }
                // state校验
                String state = (String) serachMap.get("state");
                if (state != null && !"".equals(state)) {
                    predicateList.add(criteriaBuilder.equal(root.get("state").as(String.class), state));
                }
                // recommend校验
                String recommend = (String) serachMap.get("recommend");
                if (recommend != null && !"".equals(recommend)) {
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class), recommend));
                }
                return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
            }
        };
    }

    /**
     * 带分页的条件查询
     *
     * @param searchMap 查询条件
     * @param page      当前页码
     * @param size      每页展示数量
     * @return
     */
    public Page<Label> findSearch(Map searchMap, int page, int size) {
        Specification<Label> specification = createSpecification(searchMap);
        // 页码数-1处理
        PageRequest pageRequest = PageRequest.of(page - 1, size);
        return labelDao.findAll(specification, pageRequest);
    }

}
