package com.github.vole.eureka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRegisteredEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceRenewedEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaRegistryAvailableEvent;
import org.springframework.cloud.netflix.eureka.server.event.EurekaServerStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EurekaStateChangeListener {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event) {
        //�?务下线事件
        log.info("�?务:{}|{}挂了",event.getAppName(),event.getServerId());
    }

    @EventListener
    public void listen(EurekaInstanceRegisteredEvent event) {
        //�?务注册事件
        log.info("�?务:{}|{}注册�?功了",event.getInstanceInfo().getAppName(),event.getInstanceInfo().getIPAddr());
    }

    @EventListener
    public void listen(EurekaInstanceRenewedEvent event) {
        //�?务续约事件
        log.info("心跳检测:{}|{}",event.getInstanceInfo().getAppName(),event.getInstanceInfo().getIPAddr());
    }

    @EventListener
    public void listen(EurekaRegistryAvailableEvent event) {
       //注册中心�?�动事件
    }

    @EventListener
    public void listen(EurekaServerStartedEvent event) {
        //Server�?�动
    }
}
