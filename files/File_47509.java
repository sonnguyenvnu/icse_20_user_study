package com.us.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by yangyibo on 16/12/29.
 */
@Configuration
@EnableWebSocketMessageBroker
//通过EnableWebSocketMessageBroker 开�?�使用STOMP�??议�?�传输基于代�?�(message broker)的消�?�,此时�?览器支�?使用@MessageMapping 就�?支�?@RequestMapping一样。
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer{


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //endPoint 注册�??议节点,并映射指定的URl

        //注册一个Stomp �??议的endpoint,并指定 SockJS�??议
        registry.addEndpoint("/endpointWisely").withSockJS();

        //注册一个�??字为"endpointChat" 的endpoint,并指定 SockJS�??议。   点对点-用
        registry.addEndpoint("/endpointChat").withSockJS();
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//�?置消�?�代�?�(message broker)
        //广播�?应�?置一个/topic 消�?�代�?�
        registry.enableSimpleBroker("/topic");

        //点对点�?增加一个/queue 消�?�代�?�
        registry.enableSimpleBroker("/queue","/topic");

    }
}
