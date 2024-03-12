package com.jichi.subject.domin.handler.subject;

import com.jichi.subject.common.enums.SubjectInfoTypeEnum;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目类型工厂
 * InitializingBean是Spring提供的拓展性接口，
 * InitializingBean接口为bean提供了属性初始化后的处理方法，
 * 它只有一个afterPropertiesSet方法，凡是继承该接口的类，在bean的属性初始化后都会执行该方法。
 */

@Component
public class SubjectTypeHandlerFactory implements InitializingBean {

    //里面包换了所有的Handler
    @Resource
    private List<SubjectTypeHandler> subjectTypeHandlerList;


    private Map<SubjectInfoTypeEnum, SubjectTypeHandler> handlerMap = new HashMap<>();

    public SubjectTypeHandler getHandler(int subjectType) {
        //将code的值固定
        SubjectInfoTypeEnum subjectInfoTypeEnum = SubjectInfoTypeEnum.getByCode(subjectType);
        return handlerMap.get(subjectInfoTypeEnum);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        //将所有的Handler 添加到 map当中
        for (SubjectTypeHandler subjectTypeHandler: subjectTypeHandlerList){
            //不同的 code值对应不同的handler
            handlerMap.put(subjectTypeHandler.getHandlerType(),subjectTypeHandler);
        }
    }
}
