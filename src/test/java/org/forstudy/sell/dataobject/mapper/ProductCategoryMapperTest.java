package org.forstudy.sell.dataobject.mapper;

import lombok.extern.slf4j.Slf4j;
import org.forstudy.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@Slf4j
public class ProductCategoryMapperTest {

    @Autowired
    private ProductCategoryMapper mapper;


    @Test
    public void insertByMap() throws Exception{
        Map<String,Object> map = new HashMap<>();
        map.put("category_name","折扣专区");
        map.put("category_type",11);
        int result = mapper.insertByMap(map);
        Assert.assertTrue("插入数据",result==1);
    }

    @Test
    public void insertByObjectTest(){
        int result = mapper.insertByObject(new ProductCategory("甜品热饮",new Integer(101)));
        Assert.assertTrue("插入数据",result==1);
    }

    @Test
    public void findByCategoryType(){
        ProductCategory result = mapper.findByCategoryType(10);
        Assert.assertTrue("result不为空",result!=null);
    }

    @Test
    public void updateByCategoryNameTest(){
        int result = mapper.updateByCategoryName("甜品冷饮",101);
        Assert.assertTrue("result = 1 ",result == 1);
    }

    @Test
    public void deleteByCategoryTypeTest(){
        int result = mapper.deleteByCategoryType(101);
        Assert.assertTrue("result = 1 ",result == 1);
    }
}