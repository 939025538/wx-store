package org.forstudy.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.enums.ResultEnum;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.service.BuyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderServiceImpl orderService;
    @Override
    public OrderDTO findOrderOne(String openid, String orderid) {
        return checkOwnerOrder(openid,orderid);
    }

    @Override
    public OrderDTO cancelOrderOne(String openid, String orderid) {
        OrderDTO orderDTO = checkOwnerOrder(openid,orderid);
        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOwnerOrder(String openid, String orderid){
        OrderDTO orderDTO = orderService.findOne(orderid);
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【订单详情】 该订单openid与当前用户不一致，openid={},orderid={}",orderDTO.getBuyerOpenid(),orderDTO.getOrderId());
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
