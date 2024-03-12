package com.jichi.subject.domin.handler.subject;

import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;

public interface SubjectTypeHandler {


    /**
     * 枚举身份的识别
     */
    SubjectInfoTypeEnum getHandlerType();

    /**
     * 实际的题目的插入
     */
    void add(SubjectInfoBO subjectInfoBO);
    /**
     * 查询题目的信息
     */
    SubjectOptionBO query(int subjectBoId);

}
