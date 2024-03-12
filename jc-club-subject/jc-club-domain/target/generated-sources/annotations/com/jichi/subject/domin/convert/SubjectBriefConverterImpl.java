package com.jichi.subject.domin.convert;

import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.infra.basic.entity.SubjectBrief;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-12T19:53:51+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_301 (Oracle Corporation)"
)
public class SubjectBriefConverterImpl implements SubjectBriefConverter {

    @Override
    public SubjectBrief convertBoToCategory(SubjectInfoBO subjectInfoBO) {
        if ( subjectInfoBO == null ) {
            return null;
        }

        SubjectBrief subjectBrief = new SubjectBrief();

        subjectBrief.setId( subjectInfoBO.getId() );

        return subjectBrief;
    }
}
