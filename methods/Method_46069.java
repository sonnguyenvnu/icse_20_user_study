public Id fetchConsumerSubId(){
  if (consumerConfigId == null) {
synchronized (consumerConfigIdLock) {
      if (consumerConfigId == null) {
        consumerConfigId=Lookout.registry().createId("rpc.consumer.info.stats");
      }
    }
  }
  return consumerConfigId;
}
