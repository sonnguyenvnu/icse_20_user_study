@Override public Long pfcount(byte[]... keys){
  checkIsInMultiOrPipeline();
  client.pfcount(keys);
  return client.getIntegerReply();
}
