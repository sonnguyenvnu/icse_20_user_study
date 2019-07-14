private String buildFutureKey(long appId,long collectTime,String host,int port){
  StringBuilder keyBuffer=new StringBuilder("redis-");
  keyBuffer.append(collectTime);
  keyBuffer.append("-");
  keyBuffer.append(appId);
  keyBuffer.append("-");
  keyBuffer.append(host + ":" + port);
  return keyBuffer.toString();
}
