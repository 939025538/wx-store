package org.forstudy.sell.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dataobject.OrderDetail;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.enums.OrderStatusEnums;
import org.forstudy.sell.enums.PayStatusEnums;
import org.forstudy.sell.service.OrderService;
import org.forstudy.sell.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void create() {
        OrderDTO orderDTO = new OrderDTO("周星驰","6994754-0662","CNHK","MrZhou");
        List<OrderDetail> orderDetailList = Arrays.asList(new OrderDetail("001",2)
                ,new OrderDetail("007",1));
        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderService.create(orderDTO);
        log.info("【创建订单：result:{}】",result);
    }

    @Test
    public void findOne() {
        OrderDTO orderDTO = orderService.findOne("1564048790392673812");
        log.info("OrderDTO:{}",orderDTO);
        Assert.assertNotNull(orderDTO);
    }

    @Test
    public void findList() {
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderDTO> orderDTOPage = orderService.findList(pageRequest);
        log.info("订单列表，OrderDTO：{}，getcontent:{}", orderDTOPage,JsonUtil.toJson(orderDTOPage.getContent()));
        Assert.assertTrue("查询所有的订单列表",orderDTOPage.getTotalElements()>0);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderService.findOne("1564048790392673812");
        orderDTO = orderService.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnums.CANCEL.getCode() , orderDTO.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderDTO orderDTO = orderService.findOne("1564048790392673812");
        orderDTO = orderService.finish(orderDTO);
        Assert.assertEquals(OrderStatusEnums.FINISHED.getCode() , orderDTO.getOrderStatus());
    }

    @Test
    public void pain() {
        OrderDTO orderDTO = orderService.findOne("1564048790392673812");
        orderDTO = orderService.pain(orderDTO);
        Assert.assertEquals(OrderStatusEnums.FINISHED.getCode() , orderDTO.getOrderStatus());
        Assert.assertEquals(PayStatusEnums.SUCCESS.getCode() , orderDTO.getPayStatus());
    }
}