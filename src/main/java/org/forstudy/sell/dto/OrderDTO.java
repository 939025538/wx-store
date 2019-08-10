package org.forstudy.sell.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.forstudy.sell.dataobject.OrderDetail;
import org.forstudy.sell.enums.OrderStatusEnums;
import org.forstudy.sell.enums.PayStatusEnums;
import org.forstudy.sell.utils.EnumUtil;
import org.forstudy.sell.utils.serializer.Date2LongSerializer;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@DynamicUpdate
@Data
public class OrderDTO {
    private String orderId;

    /** ·买家名字 */
    private String buyerName;

    /** ·买家电话 */
    private String buyerPhone;

    /** ·买家地址 */
    private String buyerAddress;

    /** ·买家Openid */
    private String buyerOpenid;

    /** ·订单总金额 */
    private BigDecimal orderAmount;

    /** ·订单状态 */
    private Integer orderStatus;

    /** ·支付状态 */
    private Integer payStatus;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;

    private List<OrderDetail> orderDetailList;

    public OrderDTO() {
    }

    public OrderDTO( String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid) {
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerOpenid = buyerOpenid;
    }

    @JsonIgnore
    public OrderStatusEnums getOrderStatusEnums(){
        return  EnumUtil.getByCode(orderStatus,OrderStatusEnums.class);
    }

    @JsonIgnore
    public PayStatusEnums getPayStatusEnums(){
        return EnumUtil.getByCode(payStatus , PayStatusEnums.class);
    }
}
