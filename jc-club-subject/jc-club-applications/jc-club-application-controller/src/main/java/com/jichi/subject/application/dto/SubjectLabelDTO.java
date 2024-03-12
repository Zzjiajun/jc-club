package com.jichi.subject.application.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class SubjectLabelDTO implements Serializable {
    private static final long serialVersionUID = -95735407114763760L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 标签分类
     */
    private String labelName;
    /**
     * 排序
     */
    private Integer sortNum;
    /**
     * 分类id

     */
    private Long categoryId;

}
