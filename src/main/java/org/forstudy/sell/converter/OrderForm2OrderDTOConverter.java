package org.forstudy.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dataobject.OrderDetail;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.enums.ResultEnum;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.form.OrderForm;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class OrderForm2OrderDTOConverter {

    public static OrderDTO converter(OrderForm orderForm){
        OrderDTO orderDTO = new OrderDTO();
        Gson gson = new Gson();
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try{
           orderDetailList =  gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【参数转换】转换异常，OrderDetail={}",orderForm.getItems());
            throw new SellException(ResultEnum.CONVERTER_ERROR.getCode(),e.getMessage());
        }
        orderDTO.setOrderDetailList(orderDetailList);
        orderDTO.setBuyerName(orderForm.getName());
        orderDTO.setBuyerPhone(orderForm.getPhone());
        orderDTO.setBuyerAddress(orderForm.getAddress());
        orderDTO.setBuyerOpenid(orderForm.getOpenid());


        return orderDTO;
    }
}
