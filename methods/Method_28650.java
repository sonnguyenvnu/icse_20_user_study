public Long pttl(final byte[] key){
  checkIsInMultiOrPipeline();
  client.pttl(key);
  return client.getIntegerReply();
}
