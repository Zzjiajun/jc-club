package com.jichi.subject.infra.basic.service;

import com.jichi.subject.infra.basic.entity.SubjectBrief;

import java.util.List;


/**
 * 简答题(SubjectBrief)表服务接口
 *
 * @author makejava
 * @since 2024-02-28 20:23:08
 */
public interface SubjectBriefService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectBrief queryById(Long id);

    /**
     * 分页查询
     *
     * @param subjectBrief 筛选条件
     * @return 查询结果
     */
    SubjectBrief queryByPage(SubjectBrief subjectBrief);

    /**
     * 新增数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief insert(SubjectBrief subjectBrief);

    /**
     * 修改数据
     *
     * @param subjectBrief 实例对象
     * @return 实例对象
     */
    SubjectBrief update(SubjectBrief subjectBrief);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}
