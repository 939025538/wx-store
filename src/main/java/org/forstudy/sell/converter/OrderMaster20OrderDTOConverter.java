package org.forstudy.sell.converter;

import org.forstudy.sell.dataobject.OrderMaster;
import org.forstudy.sell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMaster20OrderDTOConverter {

    public static OrderDTO converter(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster , orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> converter(List<OrderMaster> orderMasters){
        return orderMasters.stream().map( e ->
                converter(e)
                ).collect(Collectors.toList());
    }
}
