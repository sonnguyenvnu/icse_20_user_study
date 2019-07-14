@Override public byte[] getrange(byte[] key,long startOffset,long endOffset){
  client.getrange(key,startOffset,endOffset);
  return client.getBinaryBulkReply();
}
