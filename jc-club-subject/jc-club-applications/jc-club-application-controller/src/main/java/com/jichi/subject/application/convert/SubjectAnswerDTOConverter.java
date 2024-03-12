package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectAnswerDTO;
import com.jichi.subject.domin.entity.SubjectAnswerBO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectAnswerDTOConverter {

    SubjectAnswerDTOConverter INSTANCE= Mappers.getMapper(SubjectAnswerDTOConverter.class);

    List<SubjectAnswerBO> convertDOToAnswerBOs(List<SubjectAnswerDTO> subjectAnswerDTOList);

    SubjectAnswerBO convertDOToAnswerBO(SubjectAnswerDTO subjectAnswerDTO);
}
