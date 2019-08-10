package org.forstudy.sell.service.impl;

import org.forstudy.sell.dataobject.ProductInfo;
import org.forstudy.sell.dto.CartDTO;
import org.forstudy.sell.enums.ProductInfoEnums;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl ps;

    @Test
    public void findAll() {
        PageRequest requset = new PageRequest(0,5);
        Page<ProductInfo> page = ps.findAll(requset);
        System.out.println(page.getTotalElements());
    }

    @Test
    public void findByProductStatus() {
        List<ProductInfo> result = ps.findByProductStatus(ProductInfoEnums.UP.getCode());
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void save() {
        ProductInfo pi = ps.save(new ProductInfo("001","Eason演唱会门票",new BigDecimal(1280),3,"不到一分钟就抢光的演唱会门票","Eason.png",0,3));
        System.out.println(pi.toString());
        Assert.assertNotNull(pi);
    }

    @Test
    public void findOne() {
        ProductInfo pi = ps.findOne("000100");
        System.out.println(pi.toString());
        Assert.assertNotNull(pi);
    }

    @Test
    public void increaseStockTest(){
        List<CartDTO> cartDTOList = Arrays.asList(new CartDTO("001",80)
                            ,new CartDTO("017",128));
        ps.increaseStock(cartDTOList);
     }

     @Test
    public void onSaleTest(){
        ProductInfo productInfo = ps.onSale("000100");
        Assert.assertTrue("商品上架成功",productInfo.getProductStatus() == ProductInfoEnums.UP.getCode());
     }

}