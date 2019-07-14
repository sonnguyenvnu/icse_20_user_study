@Override public RedisOperateEnum addSlotsFailMaster(final long appId,int lossSlotsInstanceId,final String newMasterHost) throws Exception {
  Assert.isTrue(appId > 0);
  Assert.isTrue(lossSlotsInstanceId > 0);
  Assert.isTrue(StringUtils.isNotBlank(newMasterHost));
  AppDesc appDesc=appDao.getAppDescById(appId);
  Assert.isTrue(appDesc != null);
  int type=appDesc.getType();
  if (!TypeUtil.isRedisCluster(type)) {
    logger.error("{} is not redis cluster type",appDesc);
    return RedisOperateEnum.FAIL;
  }
  InstanceInfo lossSlotsInstanceInfo=instanceDao.getInstanceInfoById(lossSlotsInstanceId);
  Assert.isTrue(lossSlotsInstanceInfo != null);
  List<InstanceInfo> allInstanceInfo=redisCenter.getAllHealthyInstanceInfo(appId);
  if (allInstanceInfo == null || allInstanceInfo.size() == 0) {
    logger.warn("appId {} get all instance is zero",appId);
    return RedisOperateEnum.FAIL;
  }
  InstanceInfo sourceMasterInstance=allInstanceInfo.get(0);
  if (sourceMasterInstance == null) {
    logger.warn("appId {} does not have right instance",appId);
    return RedisOperateEnum.FAIL;
  }
  String healthyMasterHost=sourceMasterInstance.getIp();
  int healthyMasterPort=sourceMasterInstance.getPort();
  int healthyMasterMem=sourceMasterInstance.getMem();
  List<Integer> allLossSlots=redisCenter.getClusterLossSlots(appId,healthyMasterHost,healthyMasterPort);
  if (CollectionUtils.isEmpty(allLossSlots)) {
    logger.warn("appId {} all slots is regular and assigned",appId);
    return RedisOperateEnum.ALREADY_SUCCESS;
  }
  final List<Integer> clusterLossSlots=redisCenter.getInstanceSlots(appId,healthyMasterHost,healthyMasterPort,lossSlotsInstanceInfo.getIp(),lossSlotsInstanceInfo.getPort());
  final Integer newMasterPort=machineCenter.getAvailablePort(newMasterHost,ConstUtils.CACHE_TYPE_REDIS_CLUSTER);
  if (newMasterPort == null) {
    logger.error("host={} getAvailablePort is null",newMasterHost);
    return RedisOperateEnum.FAIL;
  }
  boolean isRun=runInstance(appDesc,newMasterHost,newMasterPort,healthyMasterMem,true);
  if (!isRun) {
    logger.error("{}:{} is not run",newMasterHost,newMasterPort);
    return RedisOperateEnum.FAIL;
  }
  boolean isCopy=copyCommonConfig(appId,healthyMasterHost,healthyMasterPort,newMasterHost,newMasterPort);
  if (!isCopy) {
    logger.error("{}:{} copy config {}:{} is error",healthyMasterHost,healthyMasterPort,newMasterHost,newMasterPort);
    return RedisOperateEnum.FAIL;
  }
  boolean isClusterMeet=false;
  Jedis sourceMasterJedis=null;
  try {
    sourceMasterJedis=redisCenter.getJedis(appId,healthyMasterHost,healthyMasterPort);
    isClusterMeet=clusterMeet(sourceMasterJedis,appId,newMasterHost,newMasterPort);
    if (!isClusterMeet) {
      logger.error("{}:{} cluster is failed",newMasterHost,newMasterPort);
      return RedisOperateEnum.FAIL;
    }
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    if (sourceMasterJedis != null) {
      sourceMasterJedis.close();
    }
  }
  if (!isClusterMeet) {
    logger.warn("{}:{} meet {}:{} is fail",healthyMasterHost,healthyMasterPort,newMasterHost,newMasterPort);
    return RedisOperateEnum.FAIL;
  }
  String nodeId=null;
  Jedis newMasterJedis=null;
  try {
    newMasterJedis=redisCenter.getJedis(appId,newMasterHost,newMasterPort,5000,5000);
    nodeId=getClusterNodeId(newMasterJedis);
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    if (newMasterJedis != null) {
      newMasterJedis.close();
    }
  }
  final String newNodeId=nodeId;
  if (StringUtils.isBlank(nodeId)) {
    logger.warn("{}:{} nodeId is empty!");
    return RedisOperateEnum.FAIL;
  }
  final List<HostAndPort> hostAndPorts=new ArrayList<>();
  hostAndPorts.add(new HostAndPort(newMasterHost,newMasterPort));
  for (  InstanceInfo instance : allInstanceInfo) {
    hostAndPorts.add(new HostAndPort(instance.getIp(),instance.getPort()));
  }
  final Map<String,Jedis> jedisMap=new HashMap<String,Jedis>();
  for (  final Integer slot : clusterLossSlots) {
    logger.warn("set slot {} start",slot);
    boolean setSlotStatus=new IdempotentConfirmer(){
      @Override public boolean execute(){
        String setSlotsResult=null;
        for (        HostAndPort hostAndPort : hostAndPorts) {
          Jedis masterJedis=null;
          try {
            String hostPort=hostAndPort.toString();
            if (jedisMap.containsKey(hostAndPort)) {
              masterJedis=jedisMap.get(hostAndPort);
            }
 else {
              masterJedis=redisCenter.getJedis(appId,hostAndPort.getHost(),hostAndPort.getPort(),5000,5000);
              jedisMap.put(hostPort,masterJedis);
            }
            setSlotsResult=masterJedis.clusterSetSlotNode(slot,newNodeId);
            logger.warn("\t {} set slot {}, result is {}",hostAndPort.toString(),slot,setSlotsResult);
          }
 catch (          JedisDataException exception) {
            logger.warn(exception.getMessage());
            try {
              TimeUnit.SECONDS.sleep(2);
            }
 catch (            InterruptedException e) {
              logger.error(e.getMessage(),e);
            }
          }
        }
        boolean nodeSetStatus=setSlotsResult != null && setSlotsResult.equalsIgnoreCase("OK");
        return nodeSetStatus;
      }
    }
.run();
    if (setSlotStatus) {
      logger.warn("set slot {} success",slot);
    }
 else {
      logger.warn("set slot {} faily",slot);
      return RedisOperateEnum.FAIL;
    }
  }
  for (  Jedis jedis : jedisMap.values()) {
    if (jedis != null) {
      jedis.close();
    }
  }
  saveInstance(appId,newMasterHost,newMasterPort,healthyMasterMem,ConstUtils.CACHE_TYPE_REDIS_CLUSTER,"");
  redisCenter.deployRedisCollection(appId,newMasterHost,newMasterPort);
  TimeUnit.SECONDS.sleep(2);
  List<Integer> currentLossSlots=redisCenter.getClusterLossSlots(appId,newMasterHost,newMasterPort);
  logger.warn("appId {} failslots assigned unsuccessfully, lossslots is {}",appId,currentLossSlots);
  return RedisOperateEnum.OP_SUCCESS;
}
