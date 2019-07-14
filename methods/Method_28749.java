@Override public Long lpushx(final String key,final String... string){
  client.lpushx(key,string);
  return client.getIntegerReply();
}
