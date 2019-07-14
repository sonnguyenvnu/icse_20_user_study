package com.crossoverjie.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Function:使用 initMethod 和 destroyMethod 的方�?
 *
 * @author crossoverJie
 *         Date: 19/03/2018 22:37
 * @since JDK 1.8
 */
@Configuration
public class LifeCycleConfig {


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public SpringLifeCycle create(){
        SpringLifeCycle springLifeCycle = new SpringLifeCycle() ;

        return springLifeCycle ;
    }
}
