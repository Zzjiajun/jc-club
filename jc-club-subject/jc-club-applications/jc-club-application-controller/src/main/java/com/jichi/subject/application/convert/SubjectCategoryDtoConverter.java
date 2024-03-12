package com.jichi.subject.application.convert;


import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.domin.entity.SubjectCategoryBo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author shkstart
 * @create 2024-02-24-20:13
 */
@Mapper
public interface SubjectCategoryDtoConverter {
    // 获取SubjectCategoryConverter的单例对象
    SubjectCategoryDtoConverter INSTANCE= Mappers.getMapper(SubjectCategoryDtoConverter.class);


    // 将SubjectCategoryBo对象转换为SubjectCategory对象的方法
    SubjectCategoryBo convertDTOToCategoryBo(SubjectCategoryDTO subjectCategoryDTO);


    List<SubjectCategoryDTO> convertBoToCategory(List<SubjectCategoryBo> subjectCategoryBoList);
}
