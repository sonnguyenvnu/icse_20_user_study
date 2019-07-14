@Override public List<String> geohash(String key,String... members){
  checkIsInMultiOrPipeline();
  client.geohash(key,members);
  return client.getMultiBulkReply();
}
