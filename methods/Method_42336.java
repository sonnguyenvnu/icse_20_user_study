/** 
 * ??????
 * @param singleConnectionFactory    ????
 * @param orderQueryQueueDestination ?????
 * @param pollingMessageListener     ?????
 * @return ??????
 */
@Bean(name="orderQueryQueueMessageListenerContainer") public DefaultMessageListenerContainer orderQueryQueueMessageListenerContainer(@Qualifier("connectionFactory") SingleConnectionFactory singleConnectionFactory,@Qualifier("orderQueryQueueDestination") ActiveMQQueue orderQueryQueueDestination,@Qualifier("pollingMessageListener") PollingMessageListener pollingMessageListener){
  DefaultMessageListenerContainer messageListenerContainer=new DefaultMessageListenerContainer();
  messageListenerContainer.setConnectionFactory(singleConnectionFactory);
  messageListenerContainer.setDestination(orderQueryQueueDestination);
  messageListenerContainer.setMessageListener(pollingMessageListener);
  return messageListenerContainer;
}
