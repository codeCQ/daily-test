package com.chenqian.daily.config;

/**
 * @author chenQian
 * @date 2021年11月24日 15:45
 */
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author :
 * description : 线程配置类
 * create time : 2020/11/25 10:18
 */
@Configuration
//开启spring托管 Async
@EnableAsync
@Data
public class ThreadPoolConfig {

    /**
     * 核心线程数
     * 默认的核心线程数为1
     *
     */
    //public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors();;
    public static final int CORE_POOL_SIZE = 1;;


    /**
     * 最大线程数
     * 默认的最大线程数是Integer.MAX_VALUE 即2<sup>31</sup>-1
     */
    public static final int MAX_POOL_SIZE =  Runtime.getRuntime().availableProcessors() * 2;
    /**
     * 缓冲队列数
     * 默认的缓冲队列数是Integer.MAX_VALUE 即2<sup>31</sup>-1
     */
    public static final int QUEUE_CAPACITY = 1000;

    /**
     * 允许线程空闲时间
     * 默认的线程空闲时间为60秒
     */
    public static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * 线程池前缀名
     */
    public static final String THREAD_NAME_PREFIX = "GENERATE_IMAGE_TASK_";

    /**
     * allowCoreThreadTimeOut为true则线程池数量最后销毁到0个
     * allowCoreThreadTimeOut为false
     * 销毁机制：超过核心线程数时，而且（超过最大值或者timeout过），就会销毁。
     * 默认是false
     */
    public boolean allowCoreThreadTimeOut = false;

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {
        TimeUnit unit;
        BlockingQueue workQueue;

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setKeepAliveSeconds(KEEP_ALIVE_SECONDS);
        taskExecutor.setThreadNamePrefix(THREAD_NAME_PREFIX);
        taskExecutor.setAllowCoreThreadTimeOut(allowCoreThreadTimeOut);
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程池初始化
        taskExecutor.initialize();
        return taskExecutor;
    }
}
