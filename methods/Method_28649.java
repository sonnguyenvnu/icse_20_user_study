@Override public byte[] objectEncoding(byte[] key){
  client.objectEncoding(key);
  return client.getBinaryBulkReply();
}
