@Override protected MessageProducer createConsumerEndpoint(ConsumerDestination destination,String group,ExtendedConsumerProperties<RocketMQConsumerProperties> consumerProperties) throws Exception {
  if (group == null || "".equals(group)) {
    throw new RuntimeException("'group must be configured for channel " + destination.getName());
  }
  RocketMQListenerBindingContainer listenerContainer=new RocketMQListenerBindingContainer(consumerProperties,rocketBinderConfigurationProperties,this);
  listenerContainer.setConsumerGroup(group);
  listenerContainer.setTopic(destination.getName());
  listenerContainer.setConsumeThreadMax(consumerProperties.getConcurrency());
  listenerContainer.setSuspendCurrentQueueTimeMillis(consumerProperties.getExtension().getSuspendCurrentQueueTimeMillis());
  listenerContainer.setDelayLevelWhenNextConsume(consumerProperties.getExtension().getDelayLevelWhenNextConsume());
  listenerContainer.setNameServer(rocketBinderConfigurationProperties.getNameServer());
  RocketMQInboundChannelAdapter rocketInboundChannelAdapter=new RocketMQInboundChannelAdapter(listenerContainer,consumerProperties,instrumentationManager);
  topicInUse.put(destination.getName(),group);
  ErrorInfrastructure errorInfrastructure=registerErrorInfrastructure(destination,group,consumerProperties);
  if (consumerProperties.getMaxAttempts() > 1) {
    rocketInboundChannelAdapter.setRetryTemplate(buildRetryTemplate(consumerProperties));
    rocketInboundChannelAdapter.setRecoveryCallback(errorInfrastructure.getRecoverer());
  }
 else {
    rocketInboundChannelAdapter.setErrorChannel(errorInfrastructure.getErrorChannel());
  }
  return rocketInboundChannelAdapter;
}
