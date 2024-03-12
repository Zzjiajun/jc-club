package com.jichi.subject.domin.handler.subject;

import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domin.convert.SubjectBriefConverter;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectBrief;
import com.jichi.subject.infra.basic.service.SubjectBriefService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 简答题目的策略类
 */


@Component
public class BriefTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectBriefService subjectBriefService;


    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.BRIEF;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        SubjectBrief subjectBrief = SubjectBriefConverter.INSTANCE.convertBoToCategory(subjectInfoBO);
        subjectBrief.setSubjectId(subjectInfoBO.getId().intValue());
        subjectBrief.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
        subjectBriefService.insert(subjectBrief);
    }

    @Override
    public SubjectOptionBO query(int subjectBoId) {
        SubjectBrief subjectBrief = new SubjectBrief();
        subjectBrief.setSubjectId(subjectBoId);
        SubjectBrief result = subjectBriefService.queryByPage(subjectBrief);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setSubjectAnswer(result.getSubjectAnswer());
        return subjectOptionBO;
    }
}
