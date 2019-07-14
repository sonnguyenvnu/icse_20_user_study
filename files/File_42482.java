package com.roncoo.pay.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.jms.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.connection.SingleConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
@PropertySource("classpath:mq_config.properties")
public class ActiveMqConfig {

    @Value("${mq.brokerURL}")
    private String mqBrokerURL;
    @Value("${mq.userName}")
    private String mqUserName;
    @Value("${mq.password}")
    private String mqPassword;
    @Value("#{10}")
    private Integer maxConnections;

    @Value("${tradeQueueName.notify}")
    private String tradeQueueDestinationName;
    @Value("${orderQueryQueueName.query}")
    private String orderQueryDestinationName;

    /**
     * 真正�?�以产生Connection的ConnectionFactory，由对应的 JMS�?务厂商�??供
     *
     * @return 真正的连接工厂
     */
    @Bean(name = "targetConnectionFactory")
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(mqBrokerURL);
        activeMQConnectionFactory.setUserName(mqUserName);
        activeMQConnectionFactory.setPassword(mqPassword);
        return activeMQConnectionFactory;
    }

    /**
     * Spring用于管�?�真正的ConnectionFactory的ConnectionFactory
     *
     * @param pooledConnectionFactory Pooled连接工厂
     * @return 连接工厂
     */
    @Primary
    @Bean(name = "connectionFactory")
    public SingleConnectionFactory singleConnectionFactory(@Qualifier("pooledConnectionFactory") PooledConnectionFactory pooledConnectionFactory) {
        SingleConnectionFactory singleConnectionFactory = new SingleConnectionFactory();
        singleConnectionFactory.setTargetConnectionFactory(pooledConnectionFactory);
        return singleConnectionFactory;
    }

    /**
     * ActiveMQ为我们�??供了一个PooledConnectionFactory，通过往里�?�注入一个ActiveMQConnectionFactory
     * �?�以用�?�将Connection�?Session和MessageProducer池化，这样�?�以大大的�?少我们的资�?消耗。
     * �?�?赖于 activemq-pool包
     *
     * @param activeMQConnectionFactory 目标连接工厂
     * @return Pooled连接工厂
     */
    @Bean(name = "pooledConnectionFactory")
    public PooledConnectionFactory pooledConnectionFactory(@Qualifier("targetConnectionFactory") ActiveMQConnectionFactory activeMQConnectionFactory) {
        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory();
        pooledConnectionFactory.setConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(maxConnections);
        return pooledConnectionFactory;
    }

    /**
     * 商户通知队列模�?�
     *
     * @param singleConnectionFactory 连接工厂
     * @return 商户通知队列模�?�
     */
    @Bean(name = "notifyJmsTemplate")
    public JmsTemplate notifyJmsTemplate(@Qualifier("connectionFactory") SingleConnectionFactory singleConnectionFactory) {
        JmsTemplate notifyJmsTemplate = new JmsTemplate();
        notifyJmsTemplate.setConnectionFactory(singleConnectionFactory);
        notifyJmsTemplate.setDefaultDestinationName(tradeQueueDestinationName);
        return notifyJmsTemplate;
    }

    /**
     * 队列模�?�
     *
     * @param singleConnectionFactory 连接工厂
     * @return 队列模�?�
     */
    @Bean(name = "jmsTemplate")
    public JmsTemplate jmsTemplate(@Qualifier("connectionFactory") SingleConnectionFactory singleConnectionFactory) {
        JmsTemplate notifyJmsTemplate = new JmsTemplate();
        notifyJmsTemplate.setConnectionFactory(singleConnectionFactory);
        notifyJmsTemplate.setDefaultDestinationName(orderQueryDestinationName);
        return notifyJmsTemplate;
    }
}
