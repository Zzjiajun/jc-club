package com.jichi.subject.common.enums;

import lombok.Getter;

/**
 * 删除状态枚举
 */
@Getter
public enum IsDeleteFlagEnum {
    DELETE(1,"已删除"),
    UN_DELETE(0,"未删除");

    public int code;

    public String desc;


    IsDeleteFlagEnum(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public static IsDeleteFlagEnum getByCode(int codeVal){
        for(IsDeleteFlagEnum resultCodeEnum : IsDeleteFlagEnum.values()){
            if(resultCodeEnum.code == codeVal){
                return resultCodeEnum;
            }
        }
        return null;
    }
}
