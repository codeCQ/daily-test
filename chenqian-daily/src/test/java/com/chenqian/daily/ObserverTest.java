package com.chenqian.daily;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.observer.MyObservable;
import com.chenqian.daily.observer.MyObserverOne;
import com.chenqian.daily.observer.MyObserverTwo;
import com.chenqian.daily.service.AnimalService;
import com.chenqian.daily.springobserver.MyApplicationEvent;
import com.chenqian.daily.springobserver.MyApplicationEventPubisher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationListener;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenQian
 * @date 2021年11月19日 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DailyApplication.class)
public class ObserverTest {

    @Autowired
    private ApplicationListener<MyApplicationEvent> listenerOne;
    @Autowired
    private MyApplicationEventPubisher pubisher;

    @Test
    public void testObserver() {
        MyObservable myObservable=new MyObservable();
        MyObserverOne one=new MyObserverOne();
        MyObserverTwo two=new MyObserverTwo();
        myObservable.attach(one);
        myObservable.attach(two);
        myObservable.setState(1);
    }

    @Test
    public void testSpringObserver() throws InterruptedException {
        MyApplicationEvent event=new MyApplicationEvent(new String("1"),"喵喵喵");
        pubisher.getApplicationEventPublisher().publishEvent(event);
        System.out.println("主线程执行中1 ...");
        Thread.sleep(1000);
        System.out.println("主线程执行中2 ...");
        while (true){

        }
    }
}
