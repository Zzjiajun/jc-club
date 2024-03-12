package com.jichi.subject.domin.entity;

import com.jichi.subject.common.entity.PageInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 题目信息表(SubjectInfo)实体类
 *
 * @author makejava
 * @since 2024-02-28 19:45:06
 */
@Data
public class SubjectInfoBO extends PageInfo implements Serializable {
    /**
     * 主键
     */
    private Long id;
    /**
     * 题目名称
     */
    private String subjectName;
    /**
     * 题目难度
     */
    private Integer subjectDifficult;
    /**
     * 出题人名
     */
    private String settleName;
    /**
     * 题目类型 1单选 2多选 3判断 4简答
     */
    private Integer subjectType;
    /**
     * 题目分数
     */
    private Integer subjectScore;
    /**
     * 题目解析
     */
    private String subjectParse;
    /**
     * 分类ids
     */
    private List<Integer> categoryIds;
    /**
     * 标签ids
     */
    private List<Integer> labelIds;
    /**
     * 答案选项
     */
    private List<SubjectAnswerBO> optionList;

    private List<String> labelName;

    private Long categoryId;

    private Long labelId;

}

