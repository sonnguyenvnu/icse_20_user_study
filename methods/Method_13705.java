private void configureDefaultProperties(Map<String,Object> source){
  String groupBindingPropertyName=createBindingPropertyName(INPUT,"group");
  String broadcastingPropertyName=createRocketMQPropertyName(INPUT,"broadcasting");
  source.put(groupBindingPropertyName,"rocketmq-bus-group");
  source.put(broadcastingPropertyName,"true");
}
