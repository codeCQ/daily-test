package com.chenqian.daily;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.service.AnimalService;
import com.chenqian.daily.service.MyAbstractFather;
import com.chenqian.daily.service.impl.MySon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenQian
 * @date 2021年12月25日 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DailyApplication.class)
public class AbstractClassTest {

    @Test
    public void testAbstractClass() {
        MySon mySon = new MySon();
        System.out.println("--------");
        MyAbstractFather myAbstractFather = new MyAbstractFather();
        System.out.println("--------");
        myAbstractFather.doSome();
    }
}
