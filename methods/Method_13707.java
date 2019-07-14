private String createBindingPropertyName(String channel,String propertyName){
  return "spring.cloud.stream.bindings." + channel + "." + propertyName;
}
