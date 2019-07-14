@Override public String scriptKill(){
  client.scriptKill();
  return client.getStatusCodeReply();
}
