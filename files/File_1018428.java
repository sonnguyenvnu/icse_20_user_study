package com.xiaolyuh.service.impl;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.xiaolyuh.entity.Person;
import com.xiaolyuh.entity.Result;
import com.xiaolyuh.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {
    private static final Logger logger = LoggerFactory.getLogger(PersonServiceImpl.class);

    @Autowired
    RedisTemplate redisTemplate;

    @HystrixCommand(groupKey = "hystrixSemaphoreTestGroupKey", commandKey = "hystrixSemaphoreTestCommandKey",
            fallbackMethod = "fallbackMethodSemaphore",
            commandProperties = {
                    //指定多久超时，�?��?毫秒。超时进fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    //判断熔断的最少请求数，默认是10；�?�有在一个统计窗�?�内处�?�的请求数�?达到这个阈值，�?会进行熔断与�?�的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //判断熔断的阈值，默认值50，表示在一个统计窗�?�内有50%的请求处�?�失败，会触�?�熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "80"),
                    //熔断多少毫秒�?�开始�?试请求 默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
                    // 最大并�?�请求数，默认10，该�?�数当使用ExecutionIsolationStrategy.SEMAPHORE策略时�?有效。
                    @HystrixProperty(name = "execution.isolation.semaphore.maxConcurrentRequests", value = "10"),
                    // 如果并�?�数达到该设置值，请求会被拒�?和抛出异常并且fallback�?会被调用。默认10
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100")
            }
    )
    @Override
    public Result semaphore(String arg) {
        redisTemplate.opsForValue().get("semaphore");
        Person person = new Person();
        person.setAge(18);
        person.setId(2L);
        person.setName("�??称semaphore");
        person.setAddress("地�?�semaphore");
        logger.info(JSON.toJSONString(person));
        return Result.success(person);
    }

    @HystrixCommand(groupKey = "hystrixThreadTestGroupKey", commandKey = "hystrixThreadTestCommandKey",
            fallbackMethod = "fallbackMethodThread",
            commandProperties = {
                    //指定多久超时，�?��?毫秒。超时进fallback
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "10000"),
                    //判断熔断的最少请求数，默认是10；�?�有在一个统计窗�?�内处�?�的请求数�?达到这个阈值，�?会进行熔断与�?�的判断
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
                    //判断熔断的阈值，默认值50，表示在一个统计窗�?�内有50%的请求处�?�失败，会触�?�熔断
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "90"),
                    //熔断多少毫秒�?�开始�?试请求 默认5000ms
                    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "60000"),
                    @HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
                    // 如果并�?�数达到该设置值，请求会被拒�?和抛出异常并且fallback�?会被调用。默认10
                    @HystrixProperty(name = "fallback.isolation.semaphore.maxConcurrentRequests", value = "100"),
                    //设置rolling percentile window的时间，默认60000
                    @HystrixProperty(name = "metrics.rollingPercentile.timeInMilliseconds", value = "600000")
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "10"),
                    @HystrixProperty(name = "maxQueueSize", value = "100"),
                    @HystrixProperty(name = "keepAliveTimeMinutes", value = "2"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets", value = "12"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "1440")
            }

    )
    @Override
    public Result thread(String arg) {
        redisTemplate.opsForValue().get("thread");
        Person person = new Person();
        person.setAge(18);
        person.setId(2L);
        person.setName("�??称thread");
        person.setAddress("地�?�thread");
        logger.info(JSON.toJSONString(person));
        return Result.success(person);
    }

    public Result fallbackMethodSemaphore(String arg, Throwable throwable) {
        logger.info("熔断�?级");
        return Result.error("熔断�?级");
    }

    public Result fallbackMethodThread(String arg) {
        logger.info("熔断�?级");
        return Result.error("熔断�?级");
    }
}
