package com.chenqian.daily.service;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("cat")
public interface AnimalService {

    void say();

    @Adaptive
    void run(URL url);

    //@Adaptive
    void fly();
}
