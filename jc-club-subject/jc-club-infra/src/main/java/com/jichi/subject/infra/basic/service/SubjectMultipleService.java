package com.jichi.subject.infra.basic.service;

import com.jichi.subject.infra.basic.entity.SubjectMultiple;

import java.util.LinkedList;
import java.util.List;

/**
 * 多选题信息表(SubjectMultiple)表服务接口
 *
 * @author makejava
 * @since 2024-02-28 20:28:41
 */
public interface SubjectMultipleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectMultiple queryById(Long id);

    /**
     * 分页查询
     *
     * @param subjectMultiple 筛选条件
     * @return 查询结果
     */
    List<SubjectMultiple> queryByPage(SubjectMultiple subjectMultiple);

    /**
     * 新增数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple insert(SubjectMultiple subjectMultiple);

    /**
     * 修改数据
     *
     * @param subjectMultiple 实例对象
     * @return 实例对象
     */
    SubjectMultiple update(SubjectMultiple subjectMultiple);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);
    /**
     * 批量插入
     *
     */
    void batchInsert(LinkedList<SubjectMultiple> subjectMultiples);
}
