package org.forstudy.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.VO.ProductVO;
import org.forstudy.sell.VO.ResultVO;
import org.forstudy.sell.converter.OrderForm2OrderDTOConverter;
import org.forstudy.sell.dataobject.OrderDetail;
import org.forstudy.sell.dto.OrderDTO;
import org.forstudy.sell.enums.ResultEnum;
import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.form.OrderForm;
import org.forstudy.sell.service.impl.BuyerServiceImpl;
import org.forstudy.sell.service.impl.OrderServiceImpl;
import org.forstudy.sell.utils.ResultVoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/buyer/order")
public class BuyerOrderController {

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private BuyerServiceImpl buyerService;

    //创建订单
    @PostMapping("/create")
    @CacheEvict(cacheNames = "buyerOrder" , key = "list" )
    public ResultVO<Map<String , String>> create(@Valid OrderForm orderForm , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】 传递参数异常，OrderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ORDER_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderDTO orderDTO = OrderForm2OrderDTOConverter.converter(orderForm);
        if (CollectionUtils.isEmpty(orderDTO.getOrderDetailList())){
            log.error("【创建订单】 购物车不能为空");
            throw new SellException(ResultEnum.CAR_ERROR);
        }
        OrderDTO resultCreate = orderService.create(orderDTO);

        Map<String,String> mapData = new HashMap<>();
        resultCreate.getOrderDetailList().stream().map(e ->
                mapData.put("orderId",e.getOrderId())
                ).collect(Collectors.toList());
//        mapData.put("orderId",resultCreate.getOrderId());
        return ResultVoUtils.success(mapData);
    }

    //订单列表
    @GetMapping("/list")
    @Cacheable(cacheNames = "buyerOrder" , key = "list" )
    public ResultVO<List<OrderDTO>> list(@RequestParam("openid") String openid ,
                                         @RequestParam(value = "page",defaultValue = "0") Integer page ,
                                         @RequestParam(value = "size",defaultValue = "10") Integer size){
        if (openid == null){
            log.error("【订单列表】用户OpenId不能为空");
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        PageRequest pageRequest = new PageRequest(page,size);
        Page<OrderDTO> orderDTOPage = orderService.findList(openid,pageRequest);
        return ResultVoUtils.success(orderDTOPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    @Cacheable(cacheNames = "buyerOrder" , key = "detail")
    public ResultVO<OrderDTO> detail(@RequestParam("openid") String openid,
                                     @RequestParam("orderid") String orderid){
        //TODO 不安全的做法，待改进
        OrderDTO orderDTO = buyerService.findOrderOne(openid, orderid);
        return ResultVoUtils.success(orderDTO);
    }

    //取消订单

    public ResultVO<OrderDetail> cancel(@RequestParam("openid") String openid,
                                        @RequestParam("orderid") String orderid){
        // TODO 不安全的做法，待改进
        OrderDTO result = buyerService.cancelOrderOne(openid, orderid);
        return ResultVoUtils.success(result);
    }
}
