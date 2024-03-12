package com.jichi.subject.domin.service.impl;

import com.alibaba.fastjson.JSON;
import com.jichi.subject.common.entity.PageResult;
import com.jichi.subject.common.enums.IsDeleteFlagEnum;
import com.jichi.subject.domin.convert.SubjectInfoConverter;
import com.jichi.subject.domin.entity.SubjectInfoBO;
import com.jichi.subject.domin.entity.SubjectOptionBO;
import com.jichi.subject.domin.handler.subject.SubjectTypeHandler;
import com.jichi.subject.domin.handler.subject.SubjectTypeHandlerFactory;
import com.jichi.subject.domin.service.SubjectInfoDomainService;
import com.jichi.subject.infra.basic.entity.SubjectInfo;
import com.jichi.subject.infra.basic.entity.SubjectLabel;
import com.jichi.subject.infra.basic.entity.SubjectMapping;
import com.jichi.subject.infra.basic.service.SubjectInfoService;
import com.jichi.subject.infra.basic.service.SubjectLabelService;
import com.jichi.subject.infra.basic.service.impl.SubjectMappingServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service("SubjectInfoDomainService")
@Slf4j
public class SubjectInfoDomainServiceImpl implements SubjectInfoDomainService {

    @Resource
    private SubjectInfoService subjectInfoService;

    @Resource
    private SubjectTypeHandlerFactory subjectTypeHandlerFactory;
    @Resource
    private SubjectMappingServiceImpl subjectMappingService;
    @Resource
    private SubjectLabelService subjectLabelService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(SubjectInfoBO subjectInfoBO) {
        //避免高并发时 不打日志的时候就不json序列化 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectInfoController.add.bo:{}", JSON.toJSONString(subjectInfoBO));
        }
        //假设流程都写在这里
        //判断 type  一大堆 if
        //工厂模式  通过根据 subjectInfoBO.getSubjectType()来获取 枚举的值
        // 工厂 的springboot的导入模式 获取所有的handler的 和枚举值对应的map 然后每次 返回的type 工厂的方法 实现不同的add方法
        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToCategory(subjectInfoBO);
        subjectInfo.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
        subjectInfoService.insert(subjectInfo);
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        subjectInfoBO.setId(subjectInfo.getId());
        handler.add(subjectInfoBO);
        LinkedList<SubjectMapping> subjectMappings = new LinkedList<>();
        List<Integer> categoryIds = subjectInfoBO.getCategoryIds();
        List<Integer> labelIds = subjectInfoBO.getLabelIds();
        categoryIds.forEach(categoryId->{
            labelIds.forEach(labelId->{
                SubjectMapping subjectMapping = new SubjectMapping();
                subjectMapping.setSubjectId(subjectInfoBO.getId());
                subjectMapping.setCategoryId(categoryId.longValue());
                subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());
                subjectMapping.setLabelId(labelId.longValue());
                subjectMappings.add(subjectMapping);
            });
        });
        subjectMappingService.batchInsert(subjectMappings);
    }


    //查询列表
    @Override
    public PageResult<SubjectInfoBO> getSubjectPage(SubjectInfoBO subjectInfoBO) {
        PageResult<SubjectInfoBO> pageResult = new PageResult<>();

        //把分页信息传入进去
        pageResult.setPageNo(subjectInfoBO.getPageNo());
        pageResult.setPageSize(subjectInfoBO.getPageSize());

        //分页开始的第一个
        int start =(pageResult.getPageNo()-1)* pageResult.getPageSize();


        //计算符合条件的记录的有多少条   不用一下查出来完记录有多少条  那样耗费时间久

        SubjectInfo subjectInfo = SubjectInfoConverter.INSTANCE.convertBoToCategory(subjectInfoBO);

        //因为 基础层未引入 domain 所以不能直接 把BO直接传入当中
        //传入
        int count=subjectInfoService.countByCondition(subjectInfo, subjectInfoBO.getCategoryId(),subjectInfoBO.getLabelId());
        if (count==0){
            return pageResult;
        }

        List<SubjectInfo> subjectInfoList = subjectInfoService.queryPage(subjectInfo, subjectInfoBO.getCategoryId(),subjectInfoBO.getLabelId(),
        start,pageResult.getPageSize());

        //因为基础层查询出来的是SubjectInfo 的集合  所以每题的标签美欧查询出来  遍历list查询每题的标签id去重  然后去label表查标签名字
        List<SubjectInfoBO> subjectInfoBOList = SubjectInfoConverter.INSTANCE.convertBoToCategory(subjectInfoList);
        subjectInfoBOList.forEach(s->{
            SubjectMapping subjectMapping = new SubjectMapping();
            subjectMapping.setSubjectId(s.getId());
            List<SubjectMapping> subjectMappingList = subjectMappingService.queryLabelId(subjectMapping);
            List<Long> labelIds = subjectMappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
            List<SubjectLabel> labelList=subjectLabelService.batchQueryById(labelIds);
            List<String> labelNames = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
            s.setLabelName(labelNames);
        });
        pageResult.setTotal(count);
        pageResult.setResult(subjectInfoBOList);
        return pageResult;
    }

    @Override
    public SubjectInfoBO querySubjectInfo1(SubjectInfoBO subjectInfoBO) {
        SubjectInfo subjectInfo = subjectInfoService.queryById(subjectInfoBO.getId());
        SubjectTypeHandler handler = subjectTypeHandlerFactory.getHandler(subjectInfo.getSubjectType());
        SubjectOptionBO optionBO = handler.query(subjectInfoBO.getId().intValue());
        SubjectInfoBO infoToBo = SubjectInfoConverter.INSTANCE. convertBoToCategory(optionBO,subjectInfo);



        SubjectMapping subjectMapping = new SubjectMapping();

        subjectMapping.setSubjectId(subjectInfo.getId());
        subjectMapping.setIsDeleted(IsDeleteFlagEnum.UN_DELETE.getCode());

        List<SubjectMapping> mappingList = subjectMappingService.queryLabelId1(subjectMapping);
        List<Long> labelIdList = mappingList.stream().map(SubjectMapping::getLabelId).collect(Collectors.toList());
        List<SubjectLabel> labelList = subjectLabelService.batchQueryById(labelIdList);
        List<String> labelNameList = labelList.stream().map(SubjectLabel::getLabelName).collect(Collectors.toList());
        infoToBo.setLabelName(labelNameList);
        return infoToBo;
    }
}
