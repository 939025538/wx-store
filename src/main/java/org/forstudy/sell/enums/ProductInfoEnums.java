package org.forstudy.sell.enums;

public enum ProductInfoEnums implements CodeEnum{
    UP(0,"在架"),
    OUT(1,"下架")
    ;

    private Integer code;
    private String description;

    ProductInfoEnums(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
