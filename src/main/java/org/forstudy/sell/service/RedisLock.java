package org.forstudy.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.exception.SellException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
@Slf4j
public class RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 加锁
     * @param key
     * @param value 当前时间+超时时间（10s）
     * @return
     */
    public boolean lock(String key , String value){
        if (redisTemplate.opsForValue().setIfAbsent(key , value)){
            return true;
        }

        String currentValue = redisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()){
            String onValue = redisTemplate.opsForValue().getAndSet(key,value);
            if (!StringUtils.isEmpty(onValue) && onValue.equals(currentValue)){
                return true;
            }
        }
        return false;
    }

    public void unlock(String key , String value){
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue) && currentValue.equals(value)){
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        }catch (Exception e){
            log.error("redis分布式锁解锁异常,{}",e);
        }

    }
}
