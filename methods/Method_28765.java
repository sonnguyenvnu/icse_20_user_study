@Override public Long clusterKeySlot(final String key){
  checkIsInMultiOrPipeline();
  client.clusterKeySlot(key);
  return client.getIntegerReply();
}
