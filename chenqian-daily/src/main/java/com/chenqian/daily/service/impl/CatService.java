package com.chenqian.daily.service.impl;

import com.alibaba.dubbo.common.URL;
import com.chenqian.daily.service.AnimalService;


/**
 * @author chenQian
 * @date 2021年11月19日 16:32
 */
public class CatService implements AnimalService {


    public void say() {
        System.out.println("cat miao miao");
    }

    @Override
    public void run(URL url) {
        System.out.println("cat -- "+url.toString());
    }

    @Override
    public void fly() {
        System.out.println("cat cannot fly");
    }

}
