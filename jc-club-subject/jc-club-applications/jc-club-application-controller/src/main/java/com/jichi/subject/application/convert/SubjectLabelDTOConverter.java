package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectLabelDTO;
import com.jichi.subject.domin.entity.SubjectLabelBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelDTOConverter {
    // 获取SubjectLabelConverter的单例对象
    SubjectLabelDTOConverter INSTANCE= Mappers.getMapper(SubjectLabelDTOConverter.class);


    // 将SubjectCategoryBo对象转换为SubjectCategory对象的方法
    SubjectLabelBo convertDTOToLabelBo(SubjectLabelDTO subjectLabelDTO);


    List<SubjectLabelDTO> convertBOToLabelDTOList(List<SubjectLabelBo> boList);
}
