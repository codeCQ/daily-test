package com.chenqian.daily;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.service.AnimalService;
import com.chenqian.daily.service.MyAbstractFather;
import com.chenqian.daily.service.impl.MyDaughter;
import com.chenqian.daily.service.impl.MySon;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenQian
 * @date 2021年12月25日 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DailyApplication.class)
public class AbstractClassTest implements ApplicationContextAware {
    private ApplicationContext applicationContext;

    //测试每个子类创建时，是否都会创建独立的父类 父类的中可继承的元素 是否是共享的
    //结论：每个子类创建时，都会执行一次父类的构造方法
    //每执行一次构造方法，就是一个全新的对象，每个子类的父类对象都是独立存在的
    //则每个父类对象的元素都是各自独立的
    @Test
    public void testAbstractClass() {
        MySon son = (MySon) applicationContext.getBean("son");
        MyDaughter daughter = (MyDaughter) applicationContext.getBean("daughter");
        son.show();
        daughter.show();

    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
