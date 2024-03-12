package com.jichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jichi.subject.application.convert.SubjectLabelDTOConverter;
import com.jichi.subject.application.dto.SubjectLabelDTO;
import com.jichi.subject.common.entity.Result;
import com.jichi.subject.domin.entity.SubjectLabelBo;
import com.jichi.subject.domin.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/subject/label")
public class SubjectLabelController {


    @Resource
    private SubjectLabelDomainService subjectLabelDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {

        try {
            //避免高并发时 先执行json序列化 浪费资源
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.do:{}", JSON.toJSONString(subjectLabelDTO));
            }

            // 类似于断言  有问题抛出
            Preconditions.checkNotNull(subjectLabelDTO.getSortNum(), "排序");
            Preconditions.checkArgument(!StringUtils.isBlank(subjectLabelDTO.getLabelName()), "标签分类不能为空");
//            Preconditions.checkNotNull(subjectLabelDTO.getId(), "id不能为空");

            SubjectLabelBo subjectLabelBo = SubjectLabelDTOConverter.INSTANCE.convertDTOToLabelBo(subjectLabelDTO);
            Boolean add = subjectLabelDomainService.add(subjectLabelBo);
            return Result.ok(add);
        } catch (Exception e) {
            log.error("SubjectLabelController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新
     * @param subjectLabelDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {

        //避免高并发时 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectLabelController.update.do:{}", JSON.toJSONString(subjectLabelDTO));
        }

        try {
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "分类id不能为空");
            SubjectLabelBo subjectLabelBo = SubjectLabelDTOConverter.INSTANCE.convertDTOToLabelBo(subjectLabelDTO);
            Boolean result = subjectLabelDomainService.update(subjectLabelBo);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }



    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        //避免高并发时 先执行json序列化 浪费资源
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.do:{}", JSON.toJSONString(subjectLabelDTO));
            }
            SubjectLabelBo subjectLabelBo = SubjectLabelDTOConverter.INSTANCE.convertDTOToLabelBo(subjectLabelDTO);
            Boolean delete = subjectLabelDomainService.delete(subjectLabelBo);
            return Result.ok(delete);
        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }

    }

    @PostMapping("queryLabelByCategoryId")
    public Result<List<SubjectLabelDTO>> queryLabelByCategoryId(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.dto:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }

            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(), "分类id不能为空");
            SubjectLabelBo subjectLabelBo = SubjectLabelDTOConverter.INSTANCE.convertDTOToLabelBo(subjectLabelDTO);
            List<SubjectLabelBo> subjectLabelBoList=subjectLabelDomainService.queryLabelByCategoryId(subjectLabelBo);
            List<SubjectLabelDTO> subjectLabelDTOS = SubjectLabelDTOConverter.INSTANCE.convertBOToLabelDTOList(subjectLabelBoList);
            return Result.ok(subjectLabelDTOS);
        } catch (Exception e) {
            log.error("SubjectLabelController.queryLabelByCategoryId.error:{}", e.getMessage(), e);
            return Result.fail("查询失败");
        }

    }
}
