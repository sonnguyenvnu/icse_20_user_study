@Override public String objectEncoding(String string){
  client.objectEncoding(string);
  return client.getBulkReply();
}
