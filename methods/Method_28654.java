@Override public long pfcount(final byte[] key){
  checkIsInMultiOrPipeline();
  client.pfcount(key);
  return client.getIntegerReply();
}
