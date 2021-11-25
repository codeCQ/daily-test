package com.chenqian.daily;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.service.AnimalService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author chenQian
 * @date 2021年11月19日 16:35
 */
@SpringBootApplication
//@ComponentScan()
public class DailyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DailyApplication.class, args);
        ExtensionLoader<AnimalService> loader = ExtensionLoader.getExtensionLoader(AnimalService.class);
        //测试SPi注解
        AnimalService catService = loader.getExtension("cat");
        catService.say();
        //测试
        AnimalService animalService = loader.getAdaptiveExtension();
        //animalService.say();
        animalService.run(new URL("dubbo", "127.0.0.1", 21880));
    }
}
