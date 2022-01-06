package com.chenqian.daily.config;

import com.chenqian.daily.service.impl.MySon;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

//@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MySon){
            System.out.println("MySon bean初始化前置接口");
        }
        return bean;
    }

    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MySon){
            System.out.println("MySon bean初始化后置接口");
        }
        return bean;
    }
}
