package com.chenqian.daily.springobserver;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MyApplicationListenerOne implements ApplicationListener<MyApplicationEvent> {

    @Async(value = "taskExecutor")
    @Override
    public void onApplicationEvent(MyApplicationEvent applicationEvent) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("监听器一号 监听到消息");
        System.out.println("监听器一号 监听到 MyApplicationEvent");
        MyApplicationEvent myApplicationEvent= (MyApplicationEvent)applicationEvent;
        System.out.println("监听器一号 监听到 MyApplicationEvent " + myApplicationEvent.getString());
        Object source = myApplicationEvent.getSource();
        Class<?> aClass = source.getClass();
        System.out.println("监听器一号 监听到 MyApplicationEvent source" + aClass.getName());
    }
}
