@Override public Long geoadd(byte[] key,double longitude,double latitude,byte[] member){
  checkIsInMultiOrPipeline();
  client.geoadd(key,longitude,latitude,member);
  return client.getIntegerReply();
}
