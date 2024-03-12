package com.jichi.subject.domin.service;

import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.domin.entity.SubjectInfoBO;

public interface SubjectInfoDomainService {


    //新增分类
    void add(SubjectInfoBO subjectInfoBO);
    /**
     * 分页查询
     */
    PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO);

    SubjectInfoBO querySubjectInfo1(SubjectInfoBO subjectInfoBO);
}
