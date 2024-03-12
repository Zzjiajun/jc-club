package com.jichi.subject.domin.service;

import com.jichi.subject.domin.entity.SubjectCategoryBo;

import java.util.List;

/**
 * @author shkstart
 * @create 2024-02-24-20:05
 */
public interface SubjectCategoryDomainService {
    //新增分类
    void add(SubjectCategoryBo subjectCategoryBo);
    //查询大类
     List<SubjectCategoryBo> queryPrimaryCategory();

    List<SubjectCategoryBo> queryCategory(SubjectCategoryBo subjectCategoryBo);

    Boolean update(SubjectCategoryBo subjectCategoryBo);

    Boolean delete(SubjectCategoryBo subjectCategoryBo);
}
