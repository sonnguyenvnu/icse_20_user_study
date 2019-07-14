public static RocketMQBinderConfigurationProperties mergeProperties(RocketMQBinderConfigurationProperties rocketBinderConfigurationProperties,RocketMQProperties rocketMQProperties){
  RocketMQBinderConfigurationProperties result=new RocketMQBinderConfigurationProperties();
  if (StringUtils.isEmpty(rocketMQProperties.getNameServer())) {
    result.setNameServer(rocketBinderConfigurationProperties.getNameServer());
  }
 else {
    result.setNameServer(rocketMQProperties.getNameServer());
  }
  if (rocketMQProperties.getProducer() == null || StringUtils.isEmpty(rocketMQProperties.getProducer().getAccessKey())) {
    result.setAccessKey(rocketBinderConfigurationProperties.getAccessKey());
  }
 else {
    result.setAccessKey(rocketMQProperties.getProducer().getAccessKey());
  }
  if (rocketMQProperties.getProducer() == null || StringUtils.isEmpty(rocketMQProperties.getProducer().getSecretKey())) {
    result.setSecretKey(rocketBinderConfigurationProperties.getSecretKey());
  }
 else {
    result.setSecretKey(rocketMQProperties.getProducer().getSecretKey());
  }
  if (rocketMQProperties.getProducer() == null || StringUtils.isEmpty(rocketMQProperties.getProducer().getCustomizedTraceTopic())) {
    result.setCustomizedTraceTopic(rocketBinderConfigurationProperties.getCustomizedTraceTopic());
  }
 else {
    result.setCustomizedTraceTopic(rocketMQProperties.getProducer().getCustomizedTraceTopic());
  }
  if (rocketMQProperties.getProducer() != null && rocketMQProperties.getProducer().isEnableMsgTrace()) {
    result.setEnableMsgTrace(Boolean.TRUE);
  }
 else {
    result.setEnableMsgTrace(rocketBinderConfigurationProperties.isEnableMsgTrace());
  }
  return result;
}
