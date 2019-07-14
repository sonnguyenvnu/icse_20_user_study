@Override public long pfcount(String... keys){
  checkIsInMultiOrPipeline();
  client.pfcount(keys);
  return client.getIntegerReply();
}
