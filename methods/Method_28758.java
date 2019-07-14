@Override public Long objectIdletime(String string){
  client.objectIdletime(string);
  return client.getIntegerReply();
}
