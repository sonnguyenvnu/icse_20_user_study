@Override public List<byte[]> slowlogGetBinary(){
  client.slowlogGet();
  return client.getBinaryMultiBulkReply();
}
