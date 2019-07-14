@Override public Long rpushx(final String key,final String... string){
  client.rpushx(key,string);
  return client.getIntegerReply();
}
