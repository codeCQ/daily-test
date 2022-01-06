package com.chenqian.daily.concurrent;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author chenQian
 * @date 2021年11月26日 16:35
 */
@Component
@Slf4j
public class VolatileTestDemo {
    private int x= 0;

    private  boolean y =false;

    private Object monitor=new Object();

    public void makeTrue(){
        y=true;
        x=42;
        log.info(Thread.currentThread().getName() +"--- makeTrue x="+x);

    }
    public void  makex() throws InterruptedException {

            log.info(Thread.currentThread().getName() +"--- makex x="+x);
        Date now = new Date();
        while (true){
            DateTime dateTime = DateUtil.offsetMillisecond(now,3000);
            if (System.currentTimeMillis() == dateTime.getTime()){
                break;
            }
        }
        log.info(Thread.currentThread().getName() +"--- makex x="+x);
    }
}
