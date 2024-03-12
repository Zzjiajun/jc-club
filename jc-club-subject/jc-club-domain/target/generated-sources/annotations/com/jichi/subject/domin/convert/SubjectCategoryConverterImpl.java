package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectCategoryBo;
import com.jichi.subject.infra.basic.entity.SubjectCategory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-08T14:42:53+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectCategoryConverterImpl implements SubjectCategoryConverter {

    @Override
    public SubjectCategory convertBoToCategory(SubjectCategoryBo subjectCategoryBo) {
        if ( subjectCategoryBo == null ) {
            return null;
        }

        SubjectCategory subjectCategory = new SubjectCategory();

        subjectCategory.setId( subjectCategoryBo.getId() );
        subjectCategory.setCategoryName( subjectCategoryBo.getCategoryName() );
        subjectCategory.setCategoryType( subjectCategoryBo.getCategoryType() );
        subjectCategory.setImageUrl( subjectCategoryBo.getImageUrl() );
        subjectCategory.setParentId( subjectCategoryBo.getParentId() );

        return subjectCategory;
    }

    @Override
    public List<SubjectCategoryBo> convertBoToCategory(List<SubjectCategory> subjectCategoryList) {
        if ( subjectCategoryList == null ) {
            return null;
        }

        List<SubjectCategoryBo> list = new ArrayList<SubjectCategoryBo>( subjectCategoryList.size() );
        for ( SubjectCategory subjectCategory : subjectCategoryList ) {
            list.add( subjectCategoryToSubjectCategoryBo( subjectCategory ) );
        }

        return list;
    }

    protected SubjectCategoryBo subjectCategoryToSubjectCategoryBo(SubjectCategory subjectCategory) {
        if ( subjectCategory == null ) {
            return null;
        }

        SubjectCategoryBo subjectCategoryBo = new SubjectCategoryBo();

        subjectCategoryBo.setId( subjectCategory.getId() );
        subjectCategoryBo.setCategoryName( subjectCategory.getCategoryName() );
        subjectCategoryBo.setCategoryType( subjectCategory.getCategoryType() );
        subjectCategoryBo.setImageUrl( subjectCategory.getImageUrl() );
        subjectCategoryBo.setParentId( subjectCategory.getParentId() );

        return subjectCategoryBo;
    }
}
