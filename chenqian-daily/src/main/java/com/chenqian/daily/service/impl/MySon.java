package com.chenqian.daily.service.impl;

import com.chenqian.daily.service.MyAbstractFather;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component("son")
public class MySon extends MyAbstractFather implements InitializingBean {
    public MySon() {
        System.out.println("MySon");
    }

    @Override
    public void doSome(){
        System.out.println("MySon doSome 执行了");
    }



    @Override
    public void afterPropertiesSet() throws Exception {
        //System.out.println("afterPropertiesSet");
    }
}
