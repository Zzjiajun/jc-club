package com.jichi.subject.application.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.jichi.subject.application.convert.SubjectCategoryDtoConverter;
import com.jichi.subject.application.dto.SubjectCategoryDTO;
import com.jichi.subject.common.entity.Result;
import com.jichi.subject.domin.entity.SubjectCategoryBo;
import com.jichi.subject.domin.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shkstart
 * @create 2024-02-24-20:21
 */
@Slf4j
@RestController
@RequestMapping("/subject/category")
public class SubjectCategoryController {

    @Resource
    //@Autowird注解默认通过byType方式注入，而@Resource注解默认通过byName方式注入
    private SubjectCategoryDomainService subjectCategoryDomainService;

    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        try {

            //避免高并发时 先执行json序列化 浪费资源
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.do:{}", JSON.toJSONString(subjectCategoryDTO));
            }

            // 类似于断言  有问题抛出
            Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(), "分类不能为空");
            Preconditions.checkArgument(StringUtils.isBlank(subjectCategoryDTO.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "id不能为空");


            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDtoConverter.INSTANCE.convertDTOToCategoryBo(subjectCategoryDTO);
            subjectCategoryDomainService.add(subjectCategoryBo);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }

    }


    @GetMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryDTO>> queryPrimaryCategory() {


        List<SubjectCategoryBo> subjectCategoryBoList = null;
        try {
            SubjectCategoryBo subjectCategoryBo = new SubjectCategoryBo();
            subjectCategoryBoList = subjectCategoryDomainService.queryCategory(subjectCategoryBo);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDtoConverter.INSTANCE.convertBoToCategory(subjectCategoryBoList);

            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.queryPrimaryCategory.error:{}", e.getMessage(), e);
            return Result.fail("查询失败");
        }
    }

    @PostMapping("/categoryByPrimary")
    public Result<List<SubjectCategoryDTO>> categoryByPrimary(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        List<SubjectCategoryBo> subjectCategoryBoList = null;
        try {
            //避免高并发时 先执行json序列化 浪费资源
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.categoryByPrimary.do:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getParentId(), "父类id不能为空");
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDtoConverter.INSTANCE.convertDTOToCategoryBo(subjectCategoryDTO);
            subjectCategoryBoList = subjectCategoryDomainService.queryCategory(subjectCategoryBo);
            List<SubjectCategoryDTO> subjectCategoryDTOList = SubjectCategoryDtoConverter.INSTANCE.convertBoToCategory(subjectCategoryBoList);
            return Result.ok(subjectCategoryDTOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.categoryByPrimary.error:{}", e.getMessage(), e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 更新
     *
     * @param subjectCategoryDTO
     * @return
     */
    @PostMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        //避免高并发时 先执行json序列化 浪费资源
        if (log.isInfoEnabled()) {
            log.info("SubjectCategoryController.update.do:{}", JSON.toJSONString(subjectCategoryDTO));
        }

        try {
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");
//        Preconditions.checkArgument(StringUtils.isEmpty(subjectCategoryDTO.getCategoryName()),"分类名称不能为空");
//        Preconditions.checkNotNull(subjectCategoryDTO.getCategoryType(),"分类类型不能为空");
//        Preconditions.checkNotNull(subjectCategoryDTO.getParentId(),"父类id不能为空");
//        Preconditions.checkArgument(StringUtils.isEmpty(subjectCategoryDTO.getImageUrl()),"图标地址不能为空");
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDtoConverter.INSTANCE.convertDTOToCategoryBo(subjectCategoryDTO);
            Boolean result = subjectCategoryDomainService.update(subjectCategoryBo);
            return Result.ok(result);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除分类
     */
    @PostMapping("/delete")
    public Result<Boolean> delete(@RequestBody SubjectCategoryDTO subjectCategoryDTO) {
        //避免高并发时 先执行json序列化 浪费资源
        try {
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.do:{}", JSON.toJSONString(subjectCategoryDTO));
            }
            Preconditions.checkNotNull(subjectCategoryDTO.getId(), "分类id不能为空");
            SubjectCategoryBo subjectCategoryBo = SubjectCategoryDtoConverter.INSTANCE.convertDTOToCategoryBo(subjectCategoryDTO);
            Boolean delete = subjectCategoryDomainService.delete(subjectCategoryBo);
            return Result.ok(delete);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.error:{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }

    }
}
