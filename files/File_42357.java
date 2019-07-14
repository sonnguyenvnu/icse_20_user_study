package com.roncoo.pay.config;

import com.roncoo.pay.app.settlement.utils.SettThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ThreadPoolTaskConfig {

    @Bean(name = "settThreadPoolExecutor", initMethod = "init", destroyMethod = "destroy")
    public SettThreadPoolExecutor settThreadPoolExecutor() {
        SettThreadPoolExecutor settThreadPoolExecutor = new SettThreadPoolExecutor();
        //最�?线程数
        settThreadPoolExecutor.setCorePoolSize(5);
        //最大线程数
        settThreadPoolExecutor.setMaxPoolSize(10);
        //线程池缓冲队列大�?
        settThreadPoolExecutor.setWorkQueueSize(256);
        //许线程闲置时间,�?��?：秒
        settThreadPoolExecutor.setKeepAliveTime(3);
        return settThreadPoolExecutor;
    }

    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(20);
        threadPoolTaskExecutor.setKeepAliveSeconds(30000);
        threadPoolTaskExecutor.setMaxPoolSize(200);
        threadPoolTaskExecutor.setQueueCapacity(1000);
        return threadPoolTaskExecutor;
    }
}
