@Override public long pfcount(final String key){
  checkIsInMultiOrPipeline();
  client.pfcount(key);
  return client.getIntegerReply();
}
