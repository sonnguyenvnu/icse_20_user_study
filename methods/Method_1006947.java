@Override protected void init(){
  Assert.notNull(this.kafkaTemplate,"KafkaTemplate must not be null.");
  Assert.notNull(this.kafkaTemplate.getDefaultTopic(),"KafkaTemplate must have the default topic set.");
}
