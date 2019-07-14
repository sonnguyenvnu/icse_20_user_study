private String createRocketMQPropertyName(String channel,String propertyName){
  return "spring.cloud.stream.rocketmq.bindings." + INPUT + ".consumer." + propertyName;
}
