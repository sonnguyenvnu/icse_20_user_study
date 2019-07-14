@Override public boolean allocateResourceApp(Long appAuditId,List<String> nodeInfoList,AppUser auditUser){
  if (appAuditId == null || appAuditId <= 0L) {
    logger.error("appAuditId is null");
    return false;
  }
  if (nodeInfoList == null || nodeInfoList.isEmpty()) {
    logger.error("nodeInfoList is null");
    return false;
  }
  AppAudit appAudit=appAuditDao.getAppAudit(appAuditId);
  if (appAudit == null) {
    logger.error("appAudit:id={} is not exist",appAuditId);
    return false;
  }
  long appId=appAudit.getAppId();
  AppDesc appDesc=appService.getByAppId(appId);
  if (appDesc == null) {
    logger.error("appDesc:id={} is not exist");
    return false;
  }
  int type=appDesc.getType();
  List<String[]> nodes=new ArrayList<String[]>();
  for (  String nodeInfo : nodeInfoList) {
    nodeInfo=StringUtils.trim(nodeInfo);
    if (StringUtils.isBlank(nodeInfo)) {
      continue;
    }
    String[] array=nodeInfo.split(":");
    nodes.add(array);
  }
  boolean isAudited=false;
  if (TypeUtil.isRedisType(type)) {
    if (TypeUtil.isRedisCluster(type)) {
      isAudited=deployCluster(appId,nodes);
    }
 else     if (nodes.size() > 0) {
      if (TypeUtil.isRedisSentinel(type)) {
        isAudited=deploySentinel(appId,nodes);
      }
 else {
        isAudited=deployStandalone(appId,nodes.get(0));
      }
    }
 else {
      logger.error("nodeInfoList={} is error",nodeInfoList);
    }
  }
 else {
    logger.error("unknown type : {}",type);
    return false;
  }
  if (isAudited) {
    appAuditDao.updateAppAudit(appAudit.getId(),AppCheckEnum.APP_ALLOCATE_RESOURCE.value());
  }
  return true;
}
