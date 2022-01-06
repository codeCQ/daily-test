package com.chenqian.daily.service.impl;

import com.chenqian.daily.service.MyAbstractFather;
import org.springframework.stereotype.Component;

@Component("daughter")
public class MyDaughter extends MyAbstractFather {
    public MyDaughter() {
        System.out.println("MyDaughter");
    }


}
