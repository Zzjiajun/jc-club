package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.infra.basic.entity.SubjectJudge;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectJudeConverter {

    SubjectJudeConverter INSTANCE= Mappers.getMapper(SubjectJudeConverter.class);


    SubjectJudge convertBoToCategory(SubjectInfoBO subjectInfoBO);

    List<SubjectAnswerBO> convertBoToCategory(List<SubjectJudge> subjectJudges);
}
