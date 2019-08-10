package org.forstudy.sell.form;

import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;

@Data
public class ProductForm {

    /** ·产品Id */
    private String productId;

    /** `商品名字 */
    private String productName;

    /** ·商品价格 */
    private BigDecimal productPrice;

    /** ·商品库存 */
    private Integer productStock;

    /** ·商品描述 */
    private String productDescription;

    /** ·商品图片链接 */
    private String productIcon;

    /** ·商品所属类目编码 */
    private Integer categoryType;
}
