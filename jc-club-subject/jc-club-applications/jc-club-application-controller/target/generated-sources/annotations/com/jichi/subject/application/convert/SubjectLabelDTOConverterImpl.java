package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectLabelDTO;
import com.jichi.subject.domin.entity.SubjectLabelBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-08T14:42:55+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectLabelDTOConverterImpl implements SubjectLabelDTOConverter {

    @Override
    public SubjectLabelBo convertDTOToLabelBo(SubjectLabelDTO subjectLabelDTO) {
        if ( subjectLabelDTO == null ) {
            return null;
        }

        SubjectLabelBo subjectLabelBo = new SubjectLabelBo();

        subjectLabelBo.setId( subjectLabelDTO.getId() );
        subjectLabelBo.setLabelName( subjectLabelDTO.getLabelName() );
        subjectLabelBo.setSortNum( subjectLabelDTO.getSortNum() );
        subjectLabelBo.setCategoryId( subjectLabelDTO.getCategoryId() );

        return subjectLabelBo;
    }

    @Override
    public List<SubjectLabelDTO> convertBOToLabelDTOList(List<SubjectLabelBo> boList) {
        if ( boList == null ) {
            return null;
        }

        List<SubjectLabelDTO> list = new ArrayList<SubjectLabelDTO>( boList.size() );
        for ( SubjectLabelBo subjectLabelBo : boList ) {
            list.add( subjectLabelBoToSubjectLabelDTO( subjectLabelBo ) );
        }

        return list;
    }

    protected SubjectLabelDTO subjectLabelBoToSubjectLabelDTO(SubjectLabelBo subjectLabelBo) {
        if ( subjectLabelBo == null ) {
            return null;
        }

        SubjectLabelDTO subjectLabelDTO = new SubjectLabelDTO();

        subjectLabelDTO.setId( subjectLabelBo.getId() );
        subjectLabelDTO.setLabelName( subjectLabelBo.getLabelName() );
        subjectLabelDTO.setSortNum( subjectLabelBo.getSortNum() );
        subjectLabelDTO.setCategoryId( subjectLabelBo.getCategoryId() );

        return subjectLabelDTO;
    }
}
