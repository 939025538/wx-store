package org.forstudy.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 分布式模拟折扣商品抢购
 */
@RequestMapping("/skill")
@RestController
@Slf4j
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @GetMapping("/query/{productId}")
    public String query(@PathVariable String productId){
        return seckillService.querySeckillProductInfo(productId);
    }

    @GetMapping("/order/{productId}")
    public String skill(@PathVariable String productId){
        log.info("@skill request,");
        seckillService.orderProductMockDiffUser(productId);
        return seckillService.querySeckillProductInfo(productId);
    }

}
