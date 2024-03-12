package com.jichi.subject.domin.handler.subject;

import com.google.common.base.Preconditions;
import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domin.convert.SubjectRadioConverter;
import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectRadio;
import com.jichi.subject.infra.basic.service.SubjectRadioService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

@Component
public class RadioTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectRadioService subjectRadioService;

    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.RADIO;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
        //因为是单选题 内容全部在optionList里面 里面有几条选项 添加几条内容
        LinkedList<SubjectRadio> subjectRadios = new LinkedList<>();
//        if (CollectionUtils.isEmpty(subjectInfoBO.getOptionList())){
//            return;
//        }
        Preconditions.checkArgument(!CollectionUtils.isEmpty(subjectInfoBO.getOptionList()),"单项题目的选项不能为空");
        subjectInfoBO.getOptionList().forEach(s->{
            SubjectRadio subjectRadio = SubjectRadioConverter.INSTANCE.convertBoToCategory(s);
            subjectRadio.setSubjectId(subjectInfoBO.getId());
            subjectRadio.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
            subjectRadios.add(subjectRadio);
        });
        subjectRadioService.batchInsert(subjectRadios);

    }

    @Override
    public SubjectOptionBO query(int subjectBoId) {
        SubjectRadio subjectRadio = new SubjectRadio();
        subjectRadio.setSubjectId(Long.valueOf(subjectBoId));
        List<SubjectRadio> subjectRadioList=subjectRadioService.queryByCondition( subjectRadio);
        List<SubjectAnswerBO> subjectAnswerBOS = SubjectRadioConverter.INSTANCE.convertBoToCategory(subjectRadioList);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
