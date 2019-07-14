/** 
 * ????????????
 * @param appId
 * @return
 */
@Override public Map<AppTopology,Object> queryAppTopology(final long appId){
  Assert.isTrue(appId > 0);
  Map<AppTopology,Object> appTopologyMap=new HashMap<AppTopology,Object>();
  AppDesc appDesc=null;
  double totalMemory=0.0;
  Set<Long> machineSet=new HashSet<Long>();
  int masterCount=0;
  int slaveCount=0;
  List<InstanceInfo> instanceInfoList=null;
  try {
    appDesc=appDao.getAppDescById(appId);
    instanceInfoList=instanceDao.getInstListByAppId(appId);
    if (appDesc == null || instanceInfoList == null || instanceInfoList.isEmpty()) {
      logger.error("get app and it's instances error? appId = {}",appId);
      return null;
    }
    if (appDesc.getType() == ConstUtils.CACHE_TYPE_REDIS_CLUSTER) {
      for (      InstanceInfo instance : instanceInfoList) {
        machineSet.add(instance.getHostId());
        totalMemory+=instance.getMem();
        Boolean isMaster=redisCenter.isMaster(appId,instance.getIp(),instance.getPort());
        if (isMaster == null) {
          continue;
        }
        if (isMaster) {
          masterCount++;
        }
 else {
          slaveCount++;
        }
      }
    }
    appTopologyMap.put(AppTopology.TOTAL_MEMORY,totalMemory / ConstUtils._1024);
    appTopologyMap.put(AppTopology.MACHINE_COUNT,machineSet.size());
    appTopologyMap.put(AppTopology.MASTER_COUNT,masterCount);
    appTopologyMap.put(AppTopology.SLAVE_COUNT,slaveCount);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return appTopologyMap;
}
