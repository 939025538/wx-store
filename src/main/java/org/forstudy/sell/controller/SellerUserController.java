package org.forstudy.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.Config.ProjectUrlConfig;
import org.forstudy.sell.constant.CookieConstant;
import org.forstudy.sell.constant.RedisConstant;
import org.forstudy.sell.dataobject.SellerInfo;
import org.forstudy.sell.enums.ResultEnum;
import org.forstudy.sell.service.SellerInfoService;
import org.forstudy.sell.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
@RequestMapping("/seller")
@Slf4j
public class SellerUserController {

    @Autowired
    private SellerInfoService sellerInfoService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    @GetMapping("/login")
    public ModelAndView login(@RequestParam("openid") String openid,
                              HttpServletResponse response,
                              Map<String,Object> map){
        SellerInfo sellerInfo = sellerInfoService.findBySellerInfoOpenid(openid);
        if (sellerInfo==null){
            map.put("msg", ResultEnum.SELLER_LOGIN_FAIL.getMsg());
            map.put("url",projectUrlConfig.getRedirectOrderList());
            return new ModelAndView("common/error",map);
        }

        String token = UUID.randomUUID().toString();

        Integer expire = RedisConstant.EXPIRE;

        //设置token到redis
        redisTemplate.opsForValue().set(String.format(RedisConstant.TOKEN_PREFIX,token),openid,expire, TimeUnit.SECONDS);

        //设置token到Cookie
        CookieUtil.set(response, CookieConstant.TOKEN,token,expire);

        return new ModelAndView("redirect:" + projectUrlConfig.getSell() + projectUrlConfig.getRedirectOrderList());
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request,
                               HttpServletResponse response,
                               Map<String , Object> map){
        //获取cookie
        Cookie cookie = CookieUtil.get(request,CookieConstant.TOKEN);
        //清除redis
        if (cookie != null){
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConstant.TOKEN_PREFIX,cookie.getValue()));
        }
        //清除cookie
        CookieUtil.set(response,CookieConstant.TOKEN,null,0);
        map.put("msg",ResultEnum.SELLER_LOGOUT_SUCCESS.getMsg());
        map.put("url",projectUrlConfig.getRedirectOrderList());

        return new ModelAndView("common/success",map);
    }
}
