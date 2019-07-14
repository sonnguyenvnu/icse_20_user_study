package com.abel.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yangyibo on 2018/6/29.
 * 使用ribbon 调用some service 并使用断路器
 */
@Service
public class SomeHystrixService {

    @Autowired
    RestTemplate restTemplate; // springboot 下使用ribbon ，�?�需注入一个 RestTemplate，springboot 已�?为我们�?�好了�?置

    @HystrixCommand(fallbackMethod = "fallbackSome") //HystrixCommand 的�?�数指定，当调用失败时，使用备用方法fallbackMethod
    public String getSome() {
        return restTemplate.getForObject("http://some/getsome", String.class);
    }

    public String fallbackSome(){
        return "some service模�?�故障";
    }
}
