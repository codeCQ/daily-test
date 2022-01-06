package com.chenqian.daily;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.service.AnimalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author chenQian
 * @date 2021年11月19日 16:35
 */
@SpringBootApplication
//@ComponentScan()
public class DailyApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DailyApplication.class, args);
        Object mySon = run.getBean("son");
        System.out.println(mySon);
    }
}
