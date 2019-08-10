package org.forstudy.sell.controller;

import com.lly835.bestpay.model.PayResponse;
import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.enums.ResultEnum;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.service.OrderService;
import org.forstudy.sell.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PayService payService;

    @GetMapping("/create")
    public ModelAndView create(@RequestParam("orderId") String orderId,
                               @RequestParam("returnUrl") String returnUrl,
                               Map<String , Object> map){

        //发起支付
        OrderDTO orderDTO = orderService.findOne(orderId);
        if (orderDTO==null){
            log.error("【订单支付】 支付错误，不存在订单，orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        PayResponse payResponse = payService.create(orderDTO);

        map.put("payResponse",payResponse);
        map.put("returnUrl",returnUrl);

        return new ModelAndView("pay/create" ,map);

    }

    @PostMapping("/notify")
    public ModelAndView notify(@RequestBody String notifyData){
        payService.notify(notifyData);
        return new ModelAndView("pay/success");
    }
}
