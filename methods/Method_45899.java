@Override protected void cacheCommonData(){
  this.serviceName=ConfigUniqueNameGenerator.getUniqueName(consumerConfig);
  this.serializeType=parseSerializeType(consumerConfig.getSerialization());
}
