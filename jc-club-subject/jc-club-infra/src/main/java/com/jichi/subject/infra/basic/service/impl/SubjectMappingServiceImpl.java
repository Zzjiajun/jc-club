package com.jichi.subject.infra.basic.service.impl;

import com.jichi.subject.infra.basic.entity.SubjectMapping;
import com.jichi.subject.infra.basic.mapper.SubjectMappingDao;
import com.jichi.subject.infra.basic.service.SubjectMappingService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * 题目分类关系表(SubjectMapping)表服务实现类
 *
 * @author makejava
 * @since 2024-02-28 18:11:34
 */
@Service("subjectMappingService")
public class SubjectMappingServiceImpl implements SubjectMappingService {
    @Resource
    private SubjectMappingDao subjectMappingDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public SubjectMapping queryById(Long id) {
        return this.subjectMappingDao.queryById(id);
    }

//    /**
//     * 分页查询
//     *
//     * @param subjectMapping 筛选条件
//     * @param pageRequest      分页对象
//     * @return 查询结果
//     */
//    @Override
//    public Page<SubjectMapping> queryByPage(SubjectMapping subjectMapping, PageRequest pageRequest) {
//        long total = this.subjectMappingDao.count(subjectMapping);
//        return new PageImpl<>(this.subjectMappingDao.queryAllByLimit(subjectMapping, pageRequest), pageRequest, total);
//    }

    /**
     * 新增数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping insert(SubjectMapping subjectMapping) {
        this.subjectMappingDao.insert(subjectMapping);
        return subjectMapping;
    }

    /**
     * 修改数据
     *
     * @param subjectMapping 实例对象
     * @return 实例对象
     */
    @Override
    public SubjectMapping update(SubjectMapping subjectMapping) {
        this.subjectMappingDao.update(subjectMapping);
        return this.queryById(subjectMapping.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.subjectMappingDao.deleteById(id) > 0;
    }

    @Override
    public List<SubjectMapping> queryLabelId(SubjectMapping subjectMapping) {
        List<SubjectMapping> subjectMappings = this.subjectMappingDao.queryDistinctLabelId(subjectMapping);
        return subjectMappings;
    }


    @Override
    public List<SubjectMapping> queryLabelId1(SubjectMapping subjectMapping) {
        List<SubjectMapping> subjectMappings = this.subjectMappingDao.queryDistinctLabelId1(subjectMapping);
        return subjectMappings;
    }


    @Override
    public void batchInsert(List<SubjectMapping> mappingList) {
        this.subjectMappingDao.insertBatch(mappingList);
    }
}
