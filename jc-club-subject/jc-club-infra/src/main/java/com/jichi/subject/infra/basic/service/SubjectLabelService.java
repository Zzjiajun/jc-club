package com.jichi.subject.infra.basic.service;

import com.jichi.subject.infra.basic.entity.SubjectLabel;

import java.util.List;

/**
 * 题目标签表(SubjectLabel)表服务接口
 *
 * @author makejava
 * @since 2024-02-27 16:47:01
 */
public interface SubjectLabelService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SubjectLabel queryById(Long id);

//    /**
//     * 分页查询
//     *
//     * @param subjectLabel 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    Page<SubjectLabel> queryByPage(SubjectLabel subjectLabel, PageRequest pageRequest);

    /**
     * 新增数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int insert(SubjectLabel subjectLabel);

    /**
     * 修改数据
     *
     * @param subjectLabel 实例对象
     * @return 实例对象
     */
    int update(SubjectLabel subjectLabel);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

    /**
     * 通过Id
     *
     */
    List<SubjectLabel> queryLabelByCategoryId(SubjectLabel subjectLabel);

    List<SubjectLabel> batchQueryById(List<Long> labelIdList);
}
