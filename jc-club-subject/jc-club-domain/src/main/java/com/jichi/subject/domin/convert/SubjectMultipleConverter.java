package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.infra.basic.entity.SubjectMultiple;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectMultipleConverter {

    SubjectMultipleConverter INSTANCE= Mappers.getMapper(SubjectMultipleConverter.class);


    SubjectMultiple convertBoToCategory(SubjectAnswerBO subjectAnswerBO);

    List<SubjectAnswerBO> convertBoToCategory(List<SubjectMultiple> subjectMultipleList);
}
