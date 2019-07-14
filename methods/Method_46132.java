@Override public void batchUnSubscribe(List<ConsumerConfig> configs){
  for (  ConsumerConfig config : configs) {
    unSubscribe(config);
  }
}
