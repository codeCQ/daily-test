package com.chenqian.daily;

import com.alibaba.dubbo.common.extension.ExtensionLoader;
import com.chenqian.daily.service.AnimalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chenQian
 * @date 2021年11月19日 16:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DailyApplication.class)
public class DubboTest {


    @Test
    public void contextLoads() {
        System.out.println("测试成功");
    }

    @Test
    public void testSPI() {
        ExtensionLoader<AnimalService> loader = ExtensionLoader.getExtensionLoader(AnimalService.class);
        AnimalService catService = loader.getExtension("cat");
        catService.miao();
    }

}
