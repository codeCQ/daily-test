package com.chenqian.daily;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.service.AnimalService;

/**
 * @author chenQian
 * @date 2021年11月19日 16:35
 */
//@SpringBootApplication
//@ComponentScan()
public class DailyApplication {
    public static void main(String[] args) {
//        SpringApplicationBuilder builder = new SpringApplicationBuilder(new Class[]{DailyApplication.class});
//        builder.run();
        ExtensionLoader<AnimalService> loader = ExtensionLoader.getExtensionLoader(AnimalService.class);

        AnimalService catService = loader.getExtension("cat");
        catService.miao();
    }
}
