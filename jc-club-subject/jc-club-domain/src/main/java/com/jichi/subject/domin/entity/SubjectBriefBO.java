package com.jichi.subject.domin.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * 简答题(SubjectBrief)实体类
 *
 * @author makejava
 * @since 2024-02-28 20:23:08
 */
@Data
public class SubjectBriefBO implements Serializable {
    private static final long serialVersionUID = 385044010339518057L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目id
     */
    private Integer subjectId;
    /**
     * 题目答案
     */
    private String subjectAnswer;

}

