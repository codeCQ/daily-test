package com.chenqian.daily;


import com.chenqian.daily.concurrent.VolatileTestDemo;
import com.chenqian.daily.config.ThreadPoolConfig;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author chenQian
 * @date 2021年11月25日 11:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DailyApplication.class)
public class ThreadTest {
    @Autowired
    private ThreadPoolConfig config;

    @Autowired
    private VolatileTestDemo  demo;
    @Autowired
    @Qualifier("taskExecutor")
    ThreadPoolTaskExecutor taskExecutor;
    @Test
    public void testHappenBefore() throws InterruptedException {
        Thread t=new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                demo.makex();
            }
        });

        t.start();
        Thread.sleep(100);
        demo.makeTrue();
        while (true){

        }


    }

    @Test
    public void testThreadPoolExecutor() {

    }
}
