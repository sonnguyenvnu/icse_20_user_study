public MessageAndMetadata<E> handle(long offset,Partition partition,String topic,String consumer,byte[] payload) throws Exception {
  E msg=process(payload);
  MessageAndMetadata<E> m=new MessageAndMetadata<>();
  m.setConsumer(consumer);
  m.setOffset(offset);
  m.setPartition(partition);
  m.setPayload(msg);
  m.setTopic(topic);
  return m;
}
