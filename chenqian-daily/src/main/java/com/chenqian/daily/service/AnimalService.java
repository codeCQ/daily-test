package com.chenqian.daily.service;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("cat")
public interface AnimalService {

    void miao();
}
