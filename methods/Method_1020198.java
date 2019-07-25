@KafkaListener(topics="${kafka.topic.default}") public void listen(ConsumerRecord<?,?> record,@Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
  Optional<?> kafkaMessage=Optional.ofNullable(record.value());
  if (kafkaMessage.isPresent()) {
    Object message=kafkaMessage.get();
    MessageEntity messageEntity=JSONObject.parseObject(message.toString(),MessageEntity.class);
    logger.info("????Topic?{}",topic);
    logger.info("????Record?{}",record);
    logger.info("????Message?{}",messageEntity);
  }
}
