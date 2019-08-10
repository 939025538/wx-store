package org.forstudy.sell.service;

import org.forstudy.sell.dto.OrderDTO;

public interface PushMessageService {
    void orderStatus(OrderDTO orderDTO);
}
