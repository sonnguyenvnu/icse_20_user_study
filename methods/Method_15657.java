@Override public AuthenticationBuilder attributes(String attributes){
  authentication.setAttributes(JSON.<Map<String,Serializable>>parseObject(attributes,Map.class));
  return this;
}
