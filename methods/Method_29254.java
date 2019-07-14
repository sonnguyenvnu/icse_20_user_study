@Override public boolean configRewrite(final long appId,final String host,final int port){
  return new IdempotentConfirmer(){
    @Override public boolean execute(){
      Jedis jedis=getJedis(appId,host,port,REDIS_DEFAULT_TIME,REDIS_DEFAULT_TIME);
      try {
        String response=jedis.configRewrite();
        return response != null && response.equalsIgnoreCase("OK");
      }
  finally {
        jedis.close();
      }
    }
  }
.run();
}
