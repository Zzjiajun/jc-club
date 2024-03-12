package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectInfoDTO;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SubjectInfoDTOConverter {


    SubjectInfoDTOConverter INSTANCE= Mappers.getMapper(SubjectInfoDTOConverter.class);

    SubjectInfoBO convertDTOToInfoBo(SubjectInfoDTO subjectInfoDTO);
    SubjectInfoDTO convertDTOToInfoBo( SubjectInfoBO subjectInfoBO);
}
