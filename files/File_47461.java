package com.abel;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yangyibo on 2018/6/28.
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class SomeApplication {
    @Value("${my.message}") //Value å€¼æ?¥è‡ªäºŽconfig server
    private String message;

    @RequestMapping(value = "/getsome")
    public String getsome(){
        return message;
    }
    public static void main(String[] args) {
        SpringApplication.run(SomeApplication.class, args);
    }
}
