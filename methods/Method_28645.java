@Override public byte[] scriptLoad(byte[] script){
  client.scriptLoad(script);
  return client.getBinaryBulkReply();
}
