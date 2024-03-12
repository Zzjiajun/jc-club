package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectAnswerBO;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.infra.basic.entity.SubjectJudge;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T19:53:51+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectJudeConverterImpl implements SubjectJudeConverter {

    @Override
    public SubjectJudge convertBoToCategory(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectJudge subjectJudge = new SubjectJudge();

        subjectJudge.setId( subjectInfoBO.getId() );

        return subjectJudge;
    }

    @Override
    public List<SubjectAnswerBO> convertBoToCategory(List<SubjectJudge> subjectJudges) {
        if ( subjectJudges == null ) {
            return null;
        }

        List<SubjectAnswerBO> list = new ArrayList<SubjectAnswerBO>( subjectJudges.size() );
        for ( SubjectJudge subjectJudge : subjectJudges ) {
            list.add( subjectJudgeToSubjectAnswerBO( subjectJudge ) );
        }

        return list;
    }

    protected SubjectAnswerBO subjectJudgeToSubjectAnswerBO(SubjectJudge subjectJudge) {
        if ( subjectJudge == null ) {
            return null;
        }

        SubjectAnswerBO subjectAnswerBO = new SubjectAnswerBO();

        subjectAnswerBO.setIsCorrect( subjectJudge.getIsCorrect() );

        return subjectAnswerBO;
    }
}
