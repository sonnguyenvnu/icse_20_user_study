@Override public HorizontalResult checkHorizontal(long appId,long appAuditId,long sourceId,long targetId,int startSlot,int endSlot,int migrateType){
  boolean isInProcess=isInProcess(appId,appAuditId,startSlot,endSlot);
  if (isInProcess) {
    return HorizontalResult.fail(String.format("appId=%s %s:%s????!",appId,startSlot,endSlot));
  }
  AppDesc appDesc=appService.getByAppId(appId);
  if (appDesc == null) {
    return HorizontalResult.fail("??????");
  }
  if (sourceId == targetId) {
    return HorizontalResult.fail(String.format("???ID=%s????????ID=%s",sourceId,targetId));
  }
  InstanceInfo sourceInstanceInfo=instanceDao.getInstanceInfoById(sourceId);
  if (sourceInstanceInfo == null) {
    return HorizontalResult.fail(String.format("???id=%s??",sourceId));
  }
  long sourceAppId=sourceInstanceInfo.getAppId();
  if (sourceAppId != appId) {
    return HorizontalResult.fail(String.format("???id=%s???appId=%s",sourceId,appId));
  }
  boolean sourceIsRun=redisCenter.isRun(appId,sourceInstanceInfo.getIp(),sourceInstanceInfo.getPort());
  if (!sourceIsRun) {
    return HorizontalResult.fail(String.format("???%s?????",sourceInstanceInfo.getHostPort()));
  }
  boolean sourceIsMaster=redisCenter.isMaster(appId,sourceInstanceInfo.getIp(),sourceInstanceInfo.getPort());
  if (!sourceIsMaster) {
    return HorizontalResult.fail(String.format("???%s??????",sourceInstanceInfo.getHostPort()));
  }
  InstanceInfo targetInstanceInfo=instanceDao.getInstanceInfoById(targetId);
  if (targetInstanceInfo == null) {
    return HorizontalResult.fail(String.format("????id=%s??",targetId));
  }
  long targetAppId=targetInstanceInfo.getAppId();
  if (targetAppId != appId) {
    return HorizontalResult.fail(String.format("????id=%s???appId=%s",targetId,appId));
  }
  boolean targetIsRun=redisCenter.isRun(appId,targetInstanceInfo.getIp(),targetInstanceInfo.getPort());
  if (!targetIsRun) {
    return HorizontalResult.fail(String.format("????%s?????",targetInstanceInfo.getHostPort()));
  }
  boolean targetIsMaster=redisCenter.isMaster(appId,targetInstanceInfo.getIp(),targetInstanceInfo.getPort());
  if (!targetIsMaster) {
    return HorizontalResult.fail(String.format("????%s??????",targetInstanceInfo.getHostPort()));
  }
  int maxSlot=16383;
  if (startSlot < 0 || startSlot > maxSlot) {
    return HorizontalResult.fail(String.format("startSlot=%s???0-%s",startSlot,maxSlot));
  }
  if (endSlot < 0 || endSlot > maxSlot) {
    return HorizontalResult.fail(String.format("endSlot=%s???0-%s",endSlot,maxSlot));
  }
  if (startSlot > endSlot) {
    return HorizontalResult.fail("startSlot????endSlot");
  }
  Map<String,InstanceSlotModel> clusterSlotsMap=redisCenter.getClusterSlotsMap(appId);
  if (MapUtils.isEmpty(clusterSlotsMap)) {
    return HorizontalResult.fail("????slot??!");
  }
  String sourceHostPort=sourceInstanceInfo.getHostPort();
  InstanceSlotModel instanceSlotModel=clusterSlotsMap.get(sourceHostPort);
  if (instanceSlotModel == null || CollectionUtils.isEmpty(instanceSlotModel.getSlotList())) {
    return HorizontalResult.fail("??????slot!");
  }
  List<Integer> slotList=instanceSlotModel.getSlotList();
  for (int i=startSlot; i <= endSlot; i++) {
    if (!slotList.contains(i)) {
      return HorizontalResult.fail(String.format("????????startSlot=%s?endSlot=%s",startSlot,endSlot));
    }
  }
  String sourceRedisVersion=redisCenter.getRedisVersion(sourceAppId,sourceInstanceInfo.getIp(),sourceInstanceInfo.getPort());
  if (StringUtils.isBlank(sourceRedisVersion)) {
    return HorizontalResult.fail(String.format("???%s????",sourceInstanceInfo.getHostPort()));
  }
  String targetRedisVersion=redisCenter.getRedisVersion(targetAppId,targetInstanceInfo.getIp(),targetInstanceInfo.getPort());
  if (StringUtils.isBlank(targetRedisVersion)) {
    return HorizontalResult.fail(String.format("????%s????",targetInstanceInfo.getHostPort()));
  }
  RedisVersion sourceRedisVersionModel=getRedisVersion(sourceRedisVersion);
  if (migrateType == 1 && !sourceRedisVersionModel.isSupportPipelineMigrate()) {
    return HorizontalResult.fail(String.format("???%s???%s,???pipeline migrate!",sourceInstanceInfo.getHostPort(),sourceRedisVersion));
  }
  RedisVersion targetRedisVersionModel=getRedisVersion(targetRedisVersion);
  if (migrateType == 1 && !targetRedisVersionModel.isSupportPipelineMigrate()) {
    return HorizontalResult.fail(String.format("????%s???%s,???pipeline migrate!",targetInstanceInfo.getHostPort(),targetRedisVersion));
  }
  return HorizontalResult.checkSuccess();
}
