/** 
 * ??????
 * @param singleConnectionFactory             ????
 * @param tradeQueueDestination               ?????
 * @param consumerSessionAwareMessageListener ?????
 * @return ??????
 */
@Bean(name="tradeQueueMessageListenerContainer") public DefaultMessageListenerContainer tradeQueueMessageListenerContainer(@Qualifier("connectionFactory") SingleConnectionFactory singleConnectionFactory,@Qualifier("tradeQueueDestination") ActiveMQQueue tradeQueueDestination,@Qualifier("consumerSessionAwareMessageListener") ConsumerSessionAwareMessageListener consumerSessionAwareMessageListener){
  DefaultMessageListenerContainer messageListenerContainer=new DefaultMessageListenerContainer();
  messageListenerContainer.setConnectionFactory(singleConnectionFactory);
  messageListenerContainer.setMessageListener(consumerSessionAwareMessageListener);
  messageListenerContainer.setDestination(tradeQueueDestination);
  return messageListenerContainer;
}
