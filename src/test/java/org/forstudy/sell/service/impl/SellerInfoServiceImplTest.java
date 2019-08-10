package org.forstudy.sell.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dataobject.SellerInfo;
import org.forstudy.sell.service.SellerInfoService;
import org.forstudy.sell.utils.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class SellerInfoServiceImplTest {

    private static final String openid = "osWL2suec6W3QDLr_M13pOaCJgXg";

    @Autowired
    private SellerInfoService sellerInfoService;

    @Test
    public void findBySellerInfoOpenid() {
        SellerInfo sellerInfo = sellerInfoService.findBySellerInfoOpenid(openid);
        log.info(JsonUtil.toJson(sellerInfo));
        Assert.assertTrue("验证查询功能",sellerInfo!=null);
    }
}