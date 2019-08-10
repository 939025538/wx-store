package org.forstudy.sell.enums;

public enum ResultVOEnums {
    SUCCESS(0,"成功"),
    LOSER(1,"失败");

    private Integer code;

    private String msg;

    ResultVOEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
