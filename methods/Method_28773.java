@Override public Long geoadd(String key,double longitude,double latitude,String member){
  checkIsInMultiOrPipeline();
  client.geoadd(key,longitude,latitude,member);
  return client.getIntegerReply();
}
