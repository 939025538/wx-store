package org.forstudy.sell.enums;

import lombok.Getter;

@Getter
public enum PayStatusEnums implements CodeEnum{

    WAIT(0,"等待支付"),
    SUCCESS(1,"已支付")
    ;

    private Integer code;

    private String msg;

    PayStatusEnums(Integer code , String msg){
        this.code = code ;
        this.msg = msg;
    }
}
