package org.forstudy.sell.repository;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dataobject.SellerInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest
public class SellerInfoRepositoryTest {

    @Autowired
    private SellerInfoRepository repository;

    @Test
    public void findByOpenid() {
        SellerInfo sellerInfo = repository.findByOpenid("osWL2suec6W3QDLr_M13pOaCJgXg");
        Assert.assertTrue("查询功能",sellerInfo!=null);
    }

    @Test
    public void saveTest(){
        SellerInfo sellerInfo = new SellerInfo("8808","939025538","950517","osWL2suec6W3QDLr_M13pOaCJgXg");
        SellerInfo result = repository.save(sellerInfo);
        Assert.assertTrue("新增功能",result!=null);
    }
}