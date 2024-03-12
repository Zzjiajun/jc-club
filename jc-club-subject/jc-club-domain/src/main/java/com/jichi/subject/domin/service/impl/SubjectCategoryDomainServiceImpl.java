package com.jichi.subject.domin.service.impl;

import com.alibaba.fastjson.JSON;
import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.domin.convert.SubjectCategoryConverter;
import com.jichi.subject.domin.entity.SubjectCategoryBo;
import com.jichi.subject.domin.service.SubjectCategoryDomainService;
import com.jichi.subject.infra.basic.entity.SubjectCategory;
import com.jichi.subject.infra.basic.service.SubjectCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 * @create 2024-02-24-20:07
 */
@Service("SubjectCategoryDomainService")
@Slf4j
public class SubjectCategoryDomainServiceImpl implements SubjectCategoryDomainService {

    @Resource
    private SubjectCategoryService subjectCategoryService;


    //插入
    public void add(SubjectCategoryBo subjectCategoryBo) {
        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.add.bo:{}", JSON.toJSONString(subjectCategoryBo));
        }

        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBo);
        subjectCategoryService.insert(subjectCategory);
    }

    @Override
    public List<SubjectCategoryBo> queryPrimaryCategory() {
        SubjectCategory subjectCategory = new SubjectCategory();
        subjectCategory.setParentId(0L);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryPrimaryCategory(subjectCategory);
        List<SubjectCategoryBo> subjectCategoryBoList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}", JSON.toJSONString(subjectCategoryBoList));
        }
        return subjectCategoryBoList;
    }

    @Override
    public List<SubjectCategoryBo> queryCategory(SubjectCategoryBo subjectCategoryBo) {
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBo);
        List<SubjectCategory> subjectCategoryList = subjectCategoryService.queryPrimaryCategory(subjectCategory);
        List<SubjectCategoryBo> subjectCategoryBoList = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryList);
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.queryPrimaryCategory.boList:{}", JSON.toJSONString(subjectCategoryBoList));
        }
        return subjectCategoryBoList;
    }

    @Override
    public Boolean update(SubjectCategoryBo subjectCategoryBo) {
        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update.bo:{}", JSON.toJSONString(subjectCategoryBo));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBo);
        int update = subjectCategoryService.update(subjectCategory);
        return update > 0;
    }

    @Override
    public Boolean delete(SubjectCategoryBo subjectCategoryBo) {
        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update.bo:{}", JSON.toJSONString(subjectCategoryBo));
        }
        SubjectCategory subjectCategory = SubjectCategoryConverter.INSTANCE.convertBoToCategory(subjectCategoryBo);
        subjectCategory.setIsDeleted(IsDeleteFlagEnum.DELETE.getCode());
        return subjectCategoryService.update(subjectCategory)>0;
    }
}
