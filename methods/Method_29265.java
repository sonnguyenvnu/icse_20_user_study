@Override public Map<String,InstanceSlotModel> getClusterSlotsMap(long appId){
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (!TypeUtil.isRedisCluster(appDesc.getType())) {
    return Collections.emptyMap();
  }
  Map<String,InstanceSlotModel> resultMap=new HashMap<String,InstanceSlotModel>();
  List<InstanceInfo> instanceList=instanceDao.getInstListByAppId(appId);
  String host=null;
  int port=0;
  for (  InstanceInfo instanceInfo : instanceList) {
    if (instanceInfo.isOffline()) {
      continue;
    }
    host=instanceInfo.getIp();
    port=instanceInfo.getPort();
    boolean isRun=isRun(appId,host,port);
    if (isRun) {
      break;
    }
  }
  if (StringUtils.isBlank(host) || port <= 0) {
    return Collections.emptyMap();
  }
  List<Object> clusterSlotList=null;
  Jedis jedis=null;
  try {
    jedis=getJedis(appId,host,port);
    clusterSlotList=jedis.clusterSlots();
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
 finally {
    if (jedis != null)     jedis.close();
  }
  if (clusterSlotList == null || clusterSlotList.size() == 0) {
    return Collections.emptyMap();
  }
  for (  Object clusterSlotObj : clusterSlotList) {
    List<Object> slotInfoList=(List<Object>)clusterSlotObj;
    if (slotInfoList.size() <= 2) {
      continue;
    }
    int startSlot=((Long)slotInfoList.get(0)).intValue();
    int endSlot=((Long)slotInfoList.get(1)).intValue();
    String slotDistribute=getStartToEndSlotDistribute(startSlot,endSlot);
    List<Integer> slotList=getStartToEndSlotList(startSlot,endSlot);
    List<Object> masterInfoList=(List<Object>)slotInfoList.get(2);
    String tempHost=SafeEncoder.encode((byte[])masterInfoList.get(0));
    int tempPort=((Long)masterInfoList.get(1)).intValue();
    String hostPort=tempHost + ":" + tempPort;
    if (resultMap.containsKey(hostPort)) {
      InstanceSlotModel instanceSlotModel=resultMap.get(hostPort);
      instanceSlotModel.getSlotDistributeList().add(slotDistribute);
      instanceSlotModel.getSlotList().addAll(slotList);
    }
 else {
      InstanceSlotModel instanceSlotModel=new InstanceSlotModel();
      instanceSlotModel.setHost(tempHost);
      instanceSlotModel.setPort(tempPort);
      List<String> slotDistributeList=new ArrayList<String>();
      slotDistributeList.add(slotDistribute);
      instanceSlotModel.setSlotDistributeList(slotDistributeList);
      instanceSlotModel.setSlotList(slotList);
      resultMap.put(hostPort,instanceSlotModel);
    }
  }
  return resultMap;
}
