package org.forstudy.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.service.OrderService;
import org.forstudy.sell.service.PayService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class PayServiceImplTest {

    @Autowired
    private PayService payService;

    @Autowired
    private OrderService orderService;

    @Test
    public void create() throws Exception {
        OrderDTO orderDTO = orderService.findOne("1564753415468853008");
        payService.create(orderDTO);
    }
}