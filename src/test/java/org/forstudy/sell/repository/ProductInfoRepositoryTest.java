package org.forstudy.sell.repository;

import org.forstudy.sell.dataobject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;
    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = repository.findByProductStatus(0);
        System.out.println(result.toString());
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void Test(){
//        ProductInfo pi = repository.save(new ProductInfo("007","元朗蛋卷",new BigDecimal(18.88),99,"好吃的蛋卷","这是一个图片的URL",0,6));
        ProductInfo pi = repository.findOne("007");
        System.out.println(pi.toString());
        Assert.assertNotNull(pi);
    }
}