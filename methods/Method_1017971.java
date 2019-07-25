@Override public void configure(Map<String,String> params){
  if (params.containsKey(ACKNOWLEDGE_WAIT_TIME_CONFIG)) {
    ackWaitTime=Long.valueOf(params.get(ACKNOWLEDGE_WAIT_TIME_CONFIG));
  }
  this.params.putAll(params);
  kafkaProducer=createProducer();
}
