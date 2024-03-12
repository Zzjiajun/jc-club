package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectLabelBo;
import com.jichi.subject.infra.basic.entity.SubjectLabel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface SubjectLabelConverter {
    // 获取SubjectCategoryConverter的单例对象
    SubjectLabelConverter INSTANCE= Mappers.getMapper(SubjectLabelConverter.class);


    // 将SubjectCategoryBo对象转换为SubjectCategory对象的方法
    SubjectLabel convertBoToCategory(SubjectLabelBo subjectLabelBo);


    List<SubjectLabelBo> convertLabelToBoList(List<SubjectLabel> subjectLabelList);

}
