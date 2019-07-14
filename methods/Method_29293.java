@Override public boolean modifyInstanceConfig(final long appId,final String host,final int port,final String parameter,final String value){
  final Jedis jedis=redisCenter.getJedis(appId,host,port,5000,5000);
  try {
    boolean isConfig=new IdempotentConfirmer(){
      @Override public boolean execute(){
        boolean isRun=redisCenter.isRun(appId,host,port);
        if (!isRun) {
          logger.warn("modifyInstanceConfig{}:{} is shutdown",host,port);
          return true;
        }
        String result=jedis.configSet(parameter,value);
        boolean isConfig=result != null && result.equalsIgnoreCase("OK");
        if (!isConfig) {
          logger.error(String.format("modifyConfigError:ip=%s,port=%s,result=%s",host,port,result));
          return false;
        }
        return isConfig;
      }
    }
.run();
    boolean isRewrite=redisCenter.configRewrite(appId,host,port);
    if (!isRewrite) {
      logger.error("configRewrite={}:{} failed",host,port);
    }
    return isConfig;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
 finally {
    if (jedis != null)     jedis.close();
  }
}
