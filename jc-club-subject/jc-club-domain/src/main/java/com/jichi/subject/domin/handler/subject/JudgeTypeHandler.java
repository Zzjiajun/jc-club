package com.jichi.subject.domin.handler.subject;

import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domin.convert.SubjectJudeConverter;
import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectJudge;
import com.jichi.subject.infra.basic.service.SubjectJudgeService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 判断题目的策略类
 */
@Component
public class JudgeTypeHandler implements SubjectTypeHandler {

    @Resource
    private SubjectJudgeService subjectJudgeService;


    @Override
    public SubjectInfoTypeEnum getHandlerType() {
        return SubjectInfoTypeEnum.JUDGE;
    }

    @Override
    public void add(SubjectInfoBO subjectInfoBO) {
//        SubjectJudge subjectJudge = SubjectJudeConverter.INSTANCE.convertBoToCategory(subjectInfoBO);
//        subjectJudge.setSubjectId(subjectInfoBO.getId());
//        subjectJudge.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
//        //获取判断题的答案 在subjectInfoBo的optionList里面的 类中的optionType 答案选项
//        subjectJudge.setIsCorrect(subjectInfoBO.getOptionList().get(0).getIsCorrect());
//        subjectJudgeService.insert(subjectJudge);
        SubjectJudge subjectJudge = new SubjectJudge();
        //获取判断题的答案 在subjectInfoBo的optionList里面的 类中的optionType 答案选项
        SubjectAnswerBO subjectAnswerBO = subjectInfoBO.getOptionList().get(0);
        subjectJudge.setSubjectId(subjectInfoBO.getId());
        subjectJudge.setIsCorrect(subjectAnswerBO.getIsCorrect());
        subjectJudge.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
        subjectJudgeService.insert(subjectJudge);
    }

    @Override
    public SubjectOptionBO query(int subjectBoId) {


//        SubjectJudge subjectJudge = new SubjectJudge();
//        subjectJudge.setSubjectId(Long.valueOf(subjectId));
//        List<SubjectJudge> result = subjectJudgeService.queryByCondition(subjectJudge);
//        List<SubjectAnswerBO> subjectAnswerBOList = JudgeSubjectConverter.INSTANCE.convertEntityToBoList(result);
//        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
//        subjectOptionBO.setOptionList(subjectAnswerBOList);
//        return subjectOptionBO;




        SubjectJudge subjectJudge = new SubjectJudge();
        subjectJudge.setSubjectId(Long.valueOf(subjectBoId));
        List<SubjectJudge> subjectJudges = subjectJudgeService.queryByPage(subjectJudge);
        List<SubjectAnswerBO> subjectAnswerBOS = SubjectJudeConverter.INSTANCE.convertBoToCategory(subjectJudges);
        SubjectOptionBO subjectOptionBO = new SubjectOptionBO();
        subjectOptionBO.setOptionList(subjectAnswerBOS);
        return subjectOptionBO;
    }
}
