package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.domin.entity.SubjectCategoryBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T19:53:53+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectCategoryDtoConverterImpl implements SubjectCategoryDtoConverter {

    @Override
    public SubjectCategoryBo convertDTOToCategoryBo(SubjectCategoryDTO subjectCategoryDTO) {
        if ( subjectCategoryDTO == null ) {
            return null;
        }

        SubjectCategoryBo subjectCategoryBo = new SubjectCategoryBo();

        subjectCategoryBo.setId( subjectCategoryDTO.getId() );
        subjectCategoryBo.setCategoryName( subjectCategoryDTO.getCategoryName() );
        subjectCategoryBo.setCategoryType( subjectCategoryDTO.getCategoryType() );
        subjectCategoryBo.setImageUrl( subjectCategoryDTO.getImageUrl() );
        subjectCategoryBo.setParentId( subjectCategoryDTO.getParentId() );

        return subjectCategoryBo;
    }

    @Override
    public List<SubjectCategoryDTO> convertBoToCategory(List<SubjectCategoryBo> subjectCategoryBoList) {
        if ( subjectCategoryBoList == null ) {
            return null;
        }

        List<SubjectCategoryDTO> list = new ArrayList<SubjectCategoryDTO>( subjectCategoryBoList.size() );
        for ( SubjectCategoryBo subjectCategoryBo : subjectCategoryBoList ) {
            list.add( subjectCategoryBoToSubjectCategoryDTO( subjectCategoryBo ) );
        }

        return list;
    }

    protected SubjectCategoryDTO subjectCategoryBoToSubjectCategoryDTO(SubjectCategoryBo subjectCategoryBo) {
        if ( subjectCategoryBo == null ) {
            return null;
        }

        SubjectCategoryDTO subjectCategoryDTO = new SubjectCategoryDTO();

        subjectCategoryDTO.setId( subjectCategoryBo.getId() );
        subjectCategoryDTO.setCategoryName( subjectCategoryBo.getCategoryName() );
        subjectCategoryDTO.setCategoryType( subjectCategoryBo.getCategoryType() );
        subjectCategoryDTO.setImageUrl( subjectCategoryBo.getImageUrl() );
        subjectCategoryDTO.setParentId( subjectCategoryBo.getParentId() );

        return subjectCategoryDTO;
    }
}
