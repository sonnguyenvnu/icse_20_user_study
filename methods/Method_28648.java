@Override public Long objectRefcount(byte[] key){
  client.objectRefcount(key);
  return client.getIntegerReply();
}
