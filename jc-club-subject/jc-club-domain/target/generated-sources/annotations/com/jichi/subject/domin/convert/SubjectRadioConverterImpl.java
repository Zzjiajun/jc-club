package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.infra.basic.entity.SubjectRadio;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T19:53:51+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectRadioConverterImpl implements SubjectRadioConverter {

    @Override
    public SubjectRadio convertBoToCategory(SubjectAnswerBO subjectAnswerBO) {
        if ( subjectAnswerBO == null ) {
            return null;
        }

        SubjectRadio subjectRadio = new SubjectRadio();

        subjectRadio.setOptionType( subjectAnswerBO.getOptionType() );
        subjectRadio.setOptionContent( subjectAnswerBO.getOptionContent() );
        subjectRadio.setIsCorrect( subjectAnswerBO.getIsCorrect() );

        return subjectRadio;
    }

    @Override
    public List<SubjectAnswerBO> convertBoToCategory(List<SubjectRadio> subjectRadios) {
        if ( subjectRadios == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectRadios.size() );
        for ( SubjectRadio subjectRadio : subjectRadios ) {
            list.add( subjectRadioToSubjectAnswerBO( subjectRadio ) );
        }

        return list;
    }

    protected SubjectAnswerBO subjectRadioToSubjectAnswerBO(SubjectRadio subjectRadio) {
        if ( subjectRadio == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setOptionType( subjectRadio.getOptionType() );
        subjectAnswerBO.setOptionContent( subjectRadio.getOptionContent() );
        subjectAnswerBO.setIsCorrect( subjectRadio.getIsCorrect() );

        return subjectAnswerBO;
    }
}
