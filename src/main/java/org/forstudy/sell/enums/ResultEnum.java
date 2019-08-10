package org.forstudy.sell.enums;

import lombok.Getter;

@Getter
public enum ResultEnum {
    ORDER_CANCEL_SUCCESS(0,"订单取消成功"),
    ORDER_FINISH_SUCCESS(1,"完结订单成功"),
    PRODUCT_STATUS_ERROR(2,"商品状态不正确"),
    SELLER_LOGIN_FAIL(3,"登陆失败，账号不正确"),
    SELLER_LOGOUT_SUCCESS(4,"登出成功"),
    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_STOCK_ERROR(20,"库存不正确"),
    ORDER_NOT_EXIST(30,"订单不存在"),
    ORDERDETAIL_NOT_EXIST(40,"订单详情不存在"),
    ORDER_STATUS_ERROR(50,"订单状态不正确"),
    ORDER_UPDATE_ERROR(60,"修改订单失败"),
    PARAM_ORDER_ERROR(70,"订单参数错误"),
    CONVERTER_ERROR(80,"参数转换异常"),
    CAR_ERROR(90,"购物车不能为空"),
    ORDER_OWNER_ERROR(100,"该订单不属于当前用户"),
    WECHAT_MP_ERROR(101,"微信公众号方面错误"),
    WXPAY_NOTIFY_MONEY_ERROR(102,"微信支付异步通知金额不一致"),
    ;

    private Integer code;

    private String msg;

     ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }


}
