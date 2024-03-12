package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.infra.basic.entity.SubjectRadio;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectRadioConverter {

    SubjectRadioConverter INSTANCE= Mappers.getMapper(SubjectRadioConverter.class);


    SubjectRadio convertBoToCategory(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertBoToCategory(List<SubjectRadio> subjectRadios);
}
