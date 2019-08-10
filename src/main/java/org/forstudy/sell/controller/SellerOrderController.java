package org.forstudy.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.Config.ProjectUrlConfig;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.enums.ResultEnum;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/seller/order")
public class SellerOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    /**
     * 订单列表
     * @param page=第几页
     * @param size=一页多少条数据
     * @return
     */
    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "size" , defaultValue = "5") Integer size){
        Map<String , Object> map = new HashMap<>();
        PageRequest request = new PageRequest(page-1, size);
        Page<OrderDTO> orderDTOPage = orderService.findList(request);
        map.put("orderDTOPage" , orderDTOPage);
        map.put("currentPage" , page);
        map.put("size",size);
        return new ModelAndView("/order/list" , map);
    }

    @GetMapping("/cancel")
    public ModelAndView cancel(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        map.put("url",projectUrlConfig.getRedirectOrderList());
        try{
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.cancel(orderDTO);
        }catch (SellException e){
            log.error("【卖家端取消订单】 发生异常{}",e);
            map.put("msg", e.getMessage());
            return new ModelAndView("common/error" , map);
        }
        map.put("msg",ResultEnum.ORDER_CANCEL_SUCCESS.getMsg());
        return new ModelAndView("common/success",map);
    }

    @GetMapping("/detail")
    public ModelAndView detail(@RequestParam("orderId") String orderId,
                               Map<String,Object> map){
        OrderDTO orderDTO;
        try{
            orderDTO = orderService.findOne(orderId);
        }catch (SellException e){
            log.error("【卖家端订单详情】发生异常{}",e);
            map.put("msg",e.getMessage());
            map.put("url",projectUrlConfig.getRedirectOrderList());
            return new ModelAndView("common/error",map);
        }
        map.put("orderDTO",orderDTO);
        return new ModelAndView("order/detail",map);
    }

    @GetMapping("/finish")
    public ModelAndView finished(@RequestParam("orderId") String orderId,
                                 Map<String,Object> map){
        map.put("url",projectUrlConfig.getRedirectOrderList());
        try {
            OrderDTO orderDTO = orderService.findOne(orderId);
            orderService.finish(orderDTO);
        }catch (SellException e){
            log.error("【卖家端完结订单】发生异常{}",e);
            map.put("msg",e.getMessage());
            return new ModelAndView("common/error",map);
        }
        map.put("msg",ResultEnum.ORDER_FINISH_SUCCESS);
        return new ModelAndView("common/success",map);
    }
}
