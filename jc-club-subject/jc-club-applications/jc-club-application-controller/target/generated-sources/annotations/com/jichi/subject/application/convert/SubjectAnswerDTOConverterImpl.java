package com.jichi.subject.application.convert;

import com.jichi.subject.application.dto.SubjectAnswerDTO;
import com.jichi.subject.domin.entity.SubjectAnswerBO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T19:53:53+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectAnswerDTOConverterImpl implements SubjectAnswerDTOConverter {

    @Override
    public List<SubjectAnswerBO> convertDOToAnswerBOs(List<SubjectAnswerDTO> subjectAnswerDTOList) {
        if ( subjectAnswerDTOList == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectAnswerDTOList.size() );
        for ( SubjectAnswerDTO subjectAnswerDTO : subjectAnswerDTOList ) {
            list.add( convertDOToAnswerBO( subjectAnswerDTO ) );
        }

        return list;
    }

    @Override
    public SubjectAnswerBO convertDOToAnswerBO(SubjectAnswerDTO subjectAnswerDTO) {
        if ( subjectAnswerDTO == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectAnswerDTO.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectAnswerDTO.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectAnswerDTO.getIsCorrect() );

        return subjectAnswerBO;
    }
}
