package com.jichi.subject.domin.service.impl;

import com.alibaba.fastjson.JSON;
import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.domin.convert.SubjectLabelConverter;
import com.jichi.subject.domin.entity.SubjectLabelBo;
import com.jichi.subject.domin.service.SubjectLabelDomainService;
import com.jichi.subject.infra.basic.entity.SubjectLabel;
import com.jichi.subject.infra.basic.entity.SubjectMapping;
import com.jichi.subject.infra.basic.service.SubjectLabelService;
import com.jichi.subject.infra.basic.service.SubjectMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("SubjectLabelDomainService")
@Slf4j
public class SubjectLabelDomainServiceImpl implements SubjectLabelDomainService {

    @Resource
    private SubjectLabelService subjectLabelService;

    @Resource
    private SubjectMappingService subjectMappingService;

    @Override
    public Boolean add(SubjectLabelBo subjectLabelBo) {

        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectLabelBo));
        }

        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToCategory(subjectLabelBo);

        int insert = subjectLabelService.insert(subjectLabel);
        return insert>0;
    }

    @Override
    public Boolean update(SubjectLabelBo subjectLabelBo) {

        //避免高并发时 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.update.do:{}", JSON.toJSONString(subjectLabelBo));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToCategory(subjectLabelBo);
        int update = subjectLabelService.update(subjectLabel);
        return update>0;
    }

    @Override
    public Boolean delete(SubjectLabelBo subjectLabelBo) {
        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.delete.do:{}", JSON.toJSONString(subjectLabelBo));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToCategory(subjectLabelBo);
        subjectLabel.setIsDeleted(IsDeleteFlagEnum.DELETE.getCode());
        return subjectLabelService.update(subjectLabel)>0;
    }

    @Override
    public List<SubjectLabelBo> queryLabelByCategoryId(SubjectLabelBo subjectLabelBo) {
        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.delete.do:{}", JSON.toJSONString(subjectLabelBo));
        }
        SubjectLabel subjectLabel = SubjectLabelConverter.INSTANCE.convertBoToCategory(subjectLabelBo);
        SubjectMapping subjectMapping = new SubjectMapping();
        subjectMapping.setCategoryId(subjectLabel.getCategoryId());
        subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
        List<SubjectMapping> subjectMappingList=subjectMappingService.queryLabelId(subjectMapping);
        if (CollectionUtils.isEmpty(subjectMappingList)){
            return Collections.emptyList();
        }
        List<Long> labelIdList =subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);

        List<SubjectLabelBo> subjectLabelBoList = SubjectLabelConverter.INSTANCE.convertLabelToBoList(labelList);

        LinkedList<SubjectLabelBo> labelBoLinkedList = new LinkedList<>();
        labelList.forEach(s->{
            SubjectLabelBo bo = new SubjectLabelBo();
            bo.setId(s.getId());
            bo.setCategoryId(subjectLabelBo.getCategoryId());
            bo.setLabelName(s.getLabelName());
            bo.setSortNum(s.getSortNum());
            labelBoLinkedList.add(bo);
        });

//        SubjectLabel label = new SubjectLabel();
//        label.setCategoryId(subjectLabelBo.getCategoryId());
//        label.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
//        List<SubjectLabel> queryLabelByCategoryIds = subjectLabelService.queryLabelByCategoryId(label);


        return labelBoLinkedList;
    }
}
