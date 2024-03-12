package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.infra.basic.entity.SubjectBrief;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectBriefConverter {

    SubjectBriefConverter INSTANCE= Mappers.getMapper(SubjectBriefConverter.class);


    SubjectBrief convertBoToCategory(SubjectInfoBO subjectInfoBO);
}
