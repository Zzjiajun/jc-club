package com.jichi.subject.domin.service;

import com.jichi.subject.domin.entity.SubjectLabelBo;

import java.util.List;

public interface SubjectLabelDomainService {
    Boolean add(SubjectLabelBo subjectLabelBo);

    Boolean update(SubjectLabelBo subjectLabelBo);

    Boolean delete(SubjectLabelBo subjectLabelBo);

    List<SubjectLabelBo> queryLabelByCategoryId(SubjectLabelBo subjectLabelBo);
}
