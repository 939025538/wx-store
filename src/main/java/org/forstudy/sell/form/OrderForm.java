package org.forstudy.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Data
public class OrderForm {

    @NotEmpty(message = "买家名字不能为空")
    private String name;

    @NotEmpty(message = "买家电话不能为空")
    private String phone;

    @NotEmpty(message = "买家地址不能为空")
    private String address;

    @NotEmpty(message = "买家Openid不能为空")
    private  String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;
}
