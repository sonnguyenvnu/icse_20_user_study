@Override public boolean importAppAndInstance(AppDesc appDesc,String appInstanceInfo){
  boolean isSuccess=true;
  try {
    appService.save(appDesc);
    long appId=appDesc.getAppId();
    appService.updateAppKey(appId);
    int type=appDesc.getType();
    appService.saveAppToUser(appId,appDesc.getUserId());
    String[] appInstanceDetails=appInstanceInfo.split("\n");
    for (    String appInstance : appInstanceDetails) {
      String[] instanceItems=appInstance.split(":");
      String host=instanceItems[0];
      int port=NumberUtils.toInt(instanceItems[1]);
      String memoryOrMasterName=instanceItems[2];
      boolean isSentinelNode=NumberUtils.toInt(memoryOrMasterName) <= 0;
      if (isSentinelNode) {
        saveInstance(appId,host,port,0,ConstUtils.CACHE_REDIS_SENTINEL,memoryOrMasterName);
      }
 else {
        if (ConstUtils.CACHE_REDIS_STANDALONE == type || ConstUtils.CACHE_REDIS_SENTINEL == type) {
          saveInstance(appId,host,port,NumberUtils.toInt(memoryOrMasterName),ConstUtils.CACHE_REDIS_STANDALONE,"");
        }
 else         if (ConstUtils.CACHE_TYPE_REDIS_CLUSTER == type) {
          saveInstance(appId,host,port,NumberUtils.toInt(memoryOrMasterName),ConstUtils.CACHE_TYPE_REDIS_CLUSTER,"");
        }
        redisCenter.deployRedisCollection(appId,host,port);
        redisCenter.deployRedisSlowLogCollection(appId,host,port);
      }
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    isSuccess=false;
  }
  return isSuccess;
}
