package com.chenqian.daily.service.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.chenqian.daily.service.AnimalService;

//@Adaptive
public class DogService implements AnimalService {
    @Override
    public void say() {
        System.out.println("dog woo woo");
    }

    @Override
    public void run(URL url) {
        System.out.println("dog -- "+url.toString());
    }

    @Override
    public void fly() {
        System.out.println("dog cannot fly");
    }


}
