package org.forstudy.sell.dataobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.forstudy.sell.enums.ProductInfoEnums;
import org.forstudy.sell.utils.EnumUtil;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@DynamicUpdate
@Data
public class ProductInfo {

    @Id
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

    /** ·商品上架状态 0：上架，1：下架*/
    private Integer productStatus = 0;

    /** ·商品所属类目编码 */
    private Integer categoryType;

    /** ·创建时间 */
    private Date createTime;

    /** ·修改时间 */
    private Date updateTime;

    public ProductInfo(){

    }


    @JsonIgnore
    public ProductInfoEnums getProductInfoEnums(){
        return EnumUtil.getByCode(productStatus,ProductInfoEnums.class);
    }

    public ProductInfo(String productId, String productName, BigDecimal productPrice, Integer productStock, String productDescription, String productIcon, Integer productStatus, Integer categoryType) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
        this.productDescription = productDescription;
        this.productIcon = productIcon;
        this.productStatus = productStatus;
        this.categoryType = categoryType;
    }

}
