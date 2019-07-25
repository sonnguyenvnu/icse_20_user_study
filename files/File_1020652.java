package com.github.vole.mq.rocket.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
@EnableConfigurationProperties(RocketmqProperties.class)
@ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "namesrvAddr")
public class RocketmqAutoConfiguration {
    @Autowired
    private RocketmqProperties properties;
    @Autowired
    private ApplicationEventPublisher publisher;

    private static boolean isFirstSub = true;

    private static long startTime = System.currentTimeMillis();

    /**
     * �?始化�?�rocketmq�?��?普通消�?�的生产者
     */
    @Bean
    @ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "producerInstanceName")
//    @ConditionalOnBean(EtcdClient.class)
    public DefaultMQProducer defaultProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用�?�维护此对象，�?�以设置为全局对象或者�?�例<br>
         * 注�?：ProducerGroupName需�?由应用�?��?�?唯一<br>
         * ProducerGroup这个概念�?��?普通的消�?�时，作用�?大，但是�?��?分布�?事务消�?�时，比较关键，
         * 因为�?务器会回查这个Group下的任�?一个Producer
         */
        DefaultMQProducer producer = new DefaultMQProducer(properties.getProducerGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerInstanceName());
        producer.setVipChannelEnabled(false);
        producer.setRetryTimesWhenSendAsyncFailed(10);

        /**
         * Producer对象在使用之�?必须�?调用start�?始化，�?始化一次�?��?�<br>
         * 注�?：切记�?�?�以在�?次�?��?消�?�时，都调用start方法
         */
        producer.start();
        log.info("RocketMq defaultProducer Started.");
        return producer;
    }

    /**
     * �?始化�?�rocketmq�?��?事务消�?�的生产者
     */
    @Bean
    @ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "producerTranInstanceName")
//    @ConditionalOnBean(EtcdClient.class)
    public TransactionMQProducer transactionProducer() throws MQClientException {
        /**
         * 一个应用创建一个Producer，由应用�?�维护此对象，�?�以设置为全局对象或者�?�例<br>
         * 注�?：ProducerGroupName需�?由应用�?��?�?唯一<br>
         * ProducerGroup这个概念�?��?普通的消�?�时，作用�?大，但是�?��?分布�?事务消�?�时，比较关键，
         * 因为�?务器会回查这个Group下的任�?一个Producer
         */
        TransactionMQProducer producer = new TransactionMQProducer(properties.getTransactionProducerGroupName());
        producer.setNamesrvAddr(properties.getNamesrvAddr());
        producer.setInstanceName(properties.getProducerTranInstanceName());
        producer.setRetryTimesWhenSendAsyncFailed(10);

        // 事务回查最�?并�?�数
        producer.setCheckThreadPoolMinSize(2);
        // 事务回查最大并�?�数
        producer.setCheckThreadPoolMaxSize(2);
        // 队列数
        producer.setCheckRequestHoldMax(2000);

        // TODO 由于社区版本的�?务器阉割调了消�?�回查的功能，所以这个地方没有�?义
        // TransactionCheckListener transactionCheckListener = new
        // TransactionCheckListenerImpl();
        // producer.setTransactionCheckListener(transactionCheckListener);

        /**
         * Producer对象在使用之�?必须�?调用start�?始化，�?始化一次�?��?�<br>
         * 注�?：切记�?�?�以在�?次�?��?消�?�时，都调用start方法
         */
        producer.start();

        log.info("RocketMq TransactionMQProducer Started.");
        return producer;
    }

    /**
     * �?始化rocketmq消�?�监�?�方�?的消费者
     */
    @Bean
    @ConditionalOnProperty(prefix = RocketmqProperties.PREFIX, value = "consumerInstanceName")
//    @ConditionalOnBean(EtcdClient.class)
    public DefaultMQPushConsumer pushConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(properties.getConsumerGroupName());
        consumer.setNamesrvAddr(properties.getNamesrvAddr());
        consumer.setInstanceName(properties.getConsumerInstanceName());
        if (properties.isConsumerBroadcasting()) {
            consumer.setMessageModel(MessageModel.BROADCASTING);
        }
        consumer.setConsumeMessageBatchMaxSize(
            properties.getConsumerBatchMaxSize() == 0 ? 1 : properties.getConsumerBatchMaxSize());// 设置批�?消费，以�??�?�消费�?��??�?，默认是1
        /**
         * 订阅指定topic下tags
         */
        List<String> subscribeList = properties.getSubscribe();
        for (String sunscribe : subscribeList) {
            consumer.subscribe(sunscribe.split(":")[0], sunscribe.split(":")[1]);
        }
        if (properties.isEnableOrderConsumer()) {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeOrderlyContext context) -> {
                try {
                    context.setAutoCommit(true);
                    msgs =filter(msgs);
                    if(msgs.size()==0) return ConsumeOrderlyStatus.SUCCESS;
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
                }
                // 如果没有return success，consumer会�?�?消费此信�?�，直到success。
                return ConsumeOrderlyStatus.SUCCESS;
            });
        } else {
            consumer.registerMessageListener((List<MessageExt> msgs, ConsumeConcurrentlyContext context) -> {
                try {
                    msgs=filter(msgs);
                    if(msgs.size()==0) return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                    this.publisher.publishEvent(new RocketmqEvent(msgs, consumer));
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER;
                }
                // 如果没有return success，consumer会�?�?消费此信�?�，直到success。
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);// 延迟5秒�?�?�动，主�?是等待spring事件监�?�相关程�?�?始化完�?，�?�则，回出现对RocketMQ的消�?�进行消费�?�立�?��?�布消�?�到达的事件，然而此事件的监�?�程�?还未�?始化，从而造�?消�?�的丢失
                    /**
                     * Consumer对象在使用之�?必须�?调用start�?始化，�?始化一次�?��?�<br>
                     */
                    try {
                        consumer.start();
                    } catch (Exception e) {
                        log.info("RocketMq pushConsumer Start failure!!!.");
                        log.error(e.getMessage(), e);
                    }
                    log.info("RocketMq pushConsumer Started.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }).start();

        return consumer;
    }

    private List<MessageExt> filter(List<MessageExt> msgs){
        if(isFirstSub&&!properties.isEnableHisConsumer()){
            msgs =msgs.stream().filter(item ->startTime - item.getBornTimestamp() < 0).collect(Collectors.toList());
        }
        if(isFirstSub && msgs.size()>0){
            isFirstSub = false;
        }
        return msgs;
    }
}
