package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;
import com.jichi.subject.infra.basic.entity.SubjectInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectInfoConverter {

    SubjectInfoConverter INSTANCE= Mappers.getMapper(SubjectInfoConverter.class);


    SubjectInfo convertBoToCategory(SubjectInfoBO subjectInfoBO);

    List<SubjectInfoBO> convertBoToCategory(List<SubjectInfo> subjectInfoList);

    SubjectInfoBO convertBoToCategory(SubjectOptionBO subjectOptionBO,SubjectInfo subjectInfo);
}
