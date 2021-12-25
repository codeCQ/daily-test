package com.chenqian.daily.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author : 清风
 * description : 线程配置类
 * create time : 2020/11/25 10:18
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    /**
     * 核心线程数
     * 默认的核心线程数为1
     *
     */
    private static final int CORE_POOL_SIZE = 5;
    /**
     * 最大线程数
     * 默认的最大线程数是Integer.MAX_VALUE 即2<sup>31</sup>-1
     */
    private static final int MAX_POOL_SIZE = 10;
    /**
     * 缓冲队列数
     * 默认的缓冲队列数是Integer.MAX_VALUE 即2<sup>31</sup>-1
     */
    private static final int QUEUE_CAPACITY = 30000;

    /**
     * 允许线程空闲时间
     * 默认的线程空闲时间为60秒
     */
    private static final int KEEP_ALIVE_SECONDS = 30;

    /**
     * 线程池前缀名
     */
    private static final String THREAD_NAME_PREFIX = "INSURANCE_TASK_";

    /**
     * allowCoreThreadTimeOut为true则线程池数量最后销毁到0个
     * allowCoreThreadTimeOut为false
     * 销毁机制：超过核心线程数时，而且（超过最大值或者timeout过），就会销毁。
     * 默认是false
     */
    private boolean allowCoreThreadTimeOut = false;

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor taskExecutor() {

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
