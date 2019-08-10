package org.forstudy.sell.dataobject;

import lombok.Data;
import org.forstudy.sell.enums.OrderStatusEnums;
import org.forstudy.sell.enums.PayStatusEnums;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@DynamicUpdate
@Data
public class OrderMaster {

    @Id
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
    private Integer orderStatus = OrderStatusEnums.NEW.getCode();

    /** ·支付状态 */
    private Integer payStatus = PayStatusEnums.WAIT.getCode();

    /** ·创建时间 */
    private Date createTime;

    /** ·修改时间 */
    private Date updateTime;

    public OrderMaster(){}

    public OrderMaster(String orderId, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, BigDecimal orderAmount, Integer orderStatus, Integer payStatus) {
        this.orderId = orderId;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerOpenid = buyerOpenid;
        this.orderAmount = orderAmount;
        this.orderStatus = orderStatus;
        this.payStatus = payStatus;
    }

    public OrderMaster(String orderId, String buyerName, String buyerPhone, String buyerAddress, String buyerOpenid, BigDecimal orderAmount) {
        this.orderId = orderId;
        this.buyerName = buyerName;
        this.buyerPhone = buyerPhone;
        this.buyerAddress = buyerAddress;
        this.buyerOpenid = buyerOpenid;
        this.orderAmount = orderAmount;
    }
}
