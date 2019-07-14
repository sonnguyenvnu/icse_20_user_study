private boolean slaveOf(final long appId,final String masterHost,final int masterPort,final String slaveHost,final int slavePort){
  final Jedis slave=redisCenter.getJedis(appId,slaveHost,slavePort,Protocol.DEFAULT_TIMEOUT * 3,Protocol.DEFAULT_TIMEOUT * 3);
  try {
    boolean isSlave=new IdempotentConfirmer(){
      @Override public boolean execute(){
        String result=slave.slaveof(masterHost,masterPort);
        return result != null && result.equalsIgnoreCase("OK");
      }
    }
.run();
    if (!isSlave) {
      logger.error(String.format("modifyAppConfig:ip=%s,port=%s failed",slaveHost,slavePort));
      return false;
    }
    redisCenter.configRewrite(appId,slaveHost,slavePort);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
 finally {
    if (slave != null)     slave.close();
  }
  return true;
}
