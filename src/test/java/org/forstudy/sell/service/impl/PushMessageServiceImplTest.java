package org.forstudy.sell.service.impl;

import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.service.OrderService;
import org.forstudy.sell.service.PushMessageService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PushMessageServiceImplTest {

    @Autowired
    private OrderService orderService;
    @Autowired
    private PushMessageService pushMessageService;

    @Test
    public void orderStatus() {
        OrderDTO orderDTO = orderService.findOne("1564845603917595232");
        pushMessageService.orderStatus(orderDTO);
    }
}