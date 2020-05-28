package org.forstudy.sell.repository;

import org.aspectj.weaver.ast.Or;
import org.forstudy.sell.dataobject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;
//添加y添嘻嘻4564654
    @Test
    public void saveTest(){
        OrderDetail orderDetail = repository.save(new OrderDetail("0002","123456","017","Jay演唱会门票", new BigDecimal(1680),2,"还没上架就卖光的演唱会门票.jpg"));
        Assert.assertNotNull(orderDetail);
    }

    @Test
    public void findByOrderId() {

    }
}
