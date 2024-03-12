package com.jichi.subject.domin.convert;


import com.jichi.subject.domin.entity.SubjectCategoryBo;
import com.jichi.subject.infra.basic.entity.SubjectCategory;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author shkstart
 * @create 2024-02-24-20:13
 */
@Mapper
public interface SubjectCategoryConverter {
    // 获取SubjectCategoryConverter的单例对象
    SubjectCategoryConverter INSTANCE= Mappers.getMapper(SubjectCategoryConverter.class);


    // 将SubjectCategoryBo对象转换为SubjectCategory对象的方法
    SubjectCategory convertBoToCategory(SubjectCategoryBo subjectCategoryBo);


    List<SubjectCategoryBo> convertBoToCategory(List<SubjectCategory> subjectCategoryList);
}
