package com.jichi.subject.domin.handler.subject;

import com.google.common.base.Preconditions;
import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domin.convert.SubjectMultipleConverter;
import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectMultiple;
import com.jichi.subject.infra.basic.service.SubjectMultipleService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选题目的策略类
 *
 */
@Component
public class MultipleTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectMultipleService subjectMultipleService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.MULTIPLE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //因为是多选题 内容全部在optionList里面 里面有几条内容 就需要插入几条多选答案
        LinkedList<SubjectMultiple> subjectMultiples = new LinkedList<>();
//        if (CollectionUtils.isEmpty(subjectInfoBO.getOptionList())){
//            return;
//        }

        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()),"多项题目的选项不能为空");
        subjectInfoBO.getOptionList().forEach(s->{
            SubjectMultiple multiple = SubjectMultipleConverter.INSTANCE.convertBoToCategory(s);
            multiple.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
            multiple.setSubjectId(subjectInfoBO.getId());
            subjectMultiples.add(multiple);
        });
        subjectMultipleService.batchInsert(subjectMultiples);
    }

    @Override
    public SubjectOptionBO query(int subjectBoId) {
        SubjectMultiple subjectMultiple = new SubjectMultiple();
        subjectMultiple.setSubjectId(Long.valueOf(subjectBoId));
        List<SubjectMultiple> subjectMultiples = subjectMultipleService.queryByPage(subjectMultiple);
        List<SubjectAnswerBO> subjectAnswerBOS = SubjectMultipleConverter.INSTANCE.convertBoToCategory(subjectMultiples);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
