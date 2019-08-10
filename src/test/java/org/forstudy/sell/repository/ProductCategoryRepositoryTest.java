package org.forstudy.sell.repository;

import org.forstudy.sell.dataobject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void save(){
        ProductCategory pc = new ProductCategory("男士专区",4);
        ProductCategory pcData = repository.save(pc);
        Assert.assertNotNull(pcData);
    }

    @Test
    public void findOne(){
        ProductCategory pc = repository.findOne(1);
        System.out.println(pc.toString());
    }

    @Test
    public void update(){
        ProductCategory pc = repository.findOne(2);
        pc.setCategoryName("女士专区");
        ProductCategory result = repository.save(pc);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByCategoryTypeInTest(){
        List<Integer> list = Arrays.asList(2,3,4);
        List<ProductCategory> result = repository.findByCategoryTypeIn(list);
        for (ProductCategory pc:result
             ) {
            System.out.println(pc.toString());
        }
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findByCategoryNameInTest(){
        List<String> list = Arrays.asList("热销榜","男士专区");
        List<ProductCategory> result = repository.findByCategoryNameIn(list);
        for (ProductCategory pc:result
        ) {
            System.out.println(pc.toString());
        }
        Assert.assertNotEquals(0,result.size());
    }
}