package org.forstudy.sell.service.impl;

import org.forstudy.sell.exception.SellException;
import org.forstudy.sell.service.RedisLock;
import org.forstudy.sell.service.SeckillService;
import org.forstudy.sell.utils.KeyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SeckillService {

    private static final int TIMEOUT = 10 * 1000;

    @Autowired
    private RedisLock redisLock;

    static Map<String , Integer> prodcuts;
    static Map<String , Integer> stock;
    static Map<String , String> orders;

    /**
     * 模拟商品表，库存表，秒杀成功订单表
     */
    static {
        prodcuts = new HashMap<>();
        stock = new HashMap<>();
        orders = new HashMap<>();
        prodcuts.put("123123",100000);
        stock.put("123123",100000);
    }

    @Override
    public String querySeckillProductInfo(String productId) {
        return "国庆特价商品限量"+prodcuts.get(productId)+"份，" +
                "剩余"+stock.get(productId)+"份" +
                "，已抢购"+orders.size()+"份！";
    }

    @Override
    public String orderProductMockDiffUser(String productId) {
        int stockNum = stock.get(productId);
        if (stockNum == 0){
            throw new SellException(403,"活动结束");
        }

        //加锁
        long time = System.currentTimeMillis() + TIMEOUT;
        if (!redisLock.lock(productId , String.valueOf(time))){
            throw new SellException(401,"手慢了一步，在试试吧！");
        }

        //模拟不同用户的OpenId
        orders.put(KeyUtils.genUniqueKey(),productId);
        //减库存
        stockNum = stockNum - 1;
        try {
            Thread.sleep(100);
        }catch (Exception e){
            e.printStackTrace();
        }
        stock.put(productId,stockNum);

        //解锁
        redisLock.unlock(productId,String.valueOf(time));

        return querySeckillProductInfo(productId);
    }
}
