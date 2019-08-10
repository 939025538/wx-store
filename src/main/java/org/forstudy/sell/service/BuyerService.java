package org.forstudy.sell.service;

import org.forstudy.sell.dto.OrderDTO;

public interface BuyerService {
    OrderDTO findOrderOne(String openid,String orderid);
    OrderDTO cancelOrderOne(String openid, String orderid);
}
