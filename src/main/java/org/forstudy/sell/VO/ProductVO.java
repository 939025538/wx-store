package org.forstudy.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ProductVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVo> productInfoVo;

    public ProductVO() {
    }

    public ProductVO(String categoryName, Integer categoryType, List<ProductInfoVo> productInfoVo) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.productInfoVo = productInfoVo;
    }
}
