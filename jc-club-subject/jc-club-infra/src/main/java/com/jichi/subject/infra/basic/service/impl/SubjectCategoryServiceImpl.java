package com.jichi.subject.infra.basic.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jichi.subject.infra.basic.entity.SubjectCategory;
import com.jichi.subject.infra.basic.mapper.SubjectCategoryDao;
import com.jichi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类(SubjectCategory)表服务实现类
 *
 * @author makejava
 * @since 2024-02-23 21:01:44
 */
@Slf4j
@Service("subjectCategoryService")
public class SubjectCategoryServiceImpl implements SubjectCategoryService {
    @Resource
    private SubjectCategoryDao subjectCategoryDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectCategory queryById(Long id) {
        return this.subjectCategoryDao.queryById(id);
    }
//
//    /**
//     * 分页查询
//     *
//     * @param subjectCategory 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<SubjectCategory> queryByPage(SubjectCategory subjectCategory, PageRequest pageRequest) {
//        long total = this.subjectCategoryDao.count(subjectCategory);
//        return new PageImpl<>(this.subjectCategoryDao.queryAllByLimit(subjectCategory, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectCategory insert(SubjectCategory subjectCategory) {

        //避免高并发时 先执行json序列化 浪费资源
        if (log.isInfoEnabled()){
            log.info("SubjectCategoryController.add.subjectCategory:{}", JSON.toJSONString(subjectCategory));
        }

        this.subjectCategoryDao.insert(subjectCategory);
        return subjectCategory;
    }

    /**
     * 修改数据
     *
     * @param subjectCategory 实例对象
     * @return 实例对象
     */
    @Override
    public int update(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.update(subjectCategory);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectCategoryDao.deleteById(id) > 0;
    }

    /**
     * 查询所有数据
     * @return
     */
    @Override
    public List<SubjectCategory> queryPrimaryCategory(SubjectCategory subjectCategory) {
        return this.subjectCategoryDao.queryPrimaryCategory(subjectCategory);
    }
}
