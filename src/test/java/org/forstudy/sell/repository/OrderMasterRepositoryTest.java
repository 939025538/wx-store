package org.forstudy.sell.repository;

import org.forstudy.sell.dataobject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
       OrderMaster orderMaster =  repository.save(new OrderMaster("123458","liubai","15625555555","西湖路99号","100100",new BigDecimal(8.5)));
       Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findByBuyerOpenid() {
        PageRequest pageRequest = new PageRequest(0,5);
        Page<OrderMaster> page = repository.findByBuyerOpenid("100100",pageRequest);
        for (OrderMaster orderMaster : page){
            System.out.println(orderMaster.toString());
        }
    }
}