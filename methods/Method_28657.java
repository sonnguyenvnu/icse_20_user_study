@Override public List<byte[]> geohash(byte[] key,byte[]... members){
  checkIsInMultiOrPipeline();
  client.geohash(key,members);
  return client.getBinaryMultiBulkReply();
}
