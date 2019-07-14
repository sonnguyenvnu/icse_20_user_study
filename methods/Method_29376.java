@Override public DataFormatCheckResult checkAppDeployDetail(Long appAuditId,String appDeployText){
  if (appAuditId == null) {
    logger.error("appAuditId is null");
    return DataFormatCheckResult.fail("??id????!");
  }
  if (StringUtils.isBlank(appDeployText)) {
    logger.error("appDeployText is null");
    return DataFormatCheckResult.fail("??????????!");
  }
  String[] nodeInfoList=appDeployText.split(ConstUtils.NEXT_LINE);
  if (nodeInfoList == null || nodeInfoList.length == 0) {
    logger.error("nodeInfoList is null");
    return DataFormatCheckResult.fail("??????????!");
  }
  AppAudit appAudit=appAuditDao.getAppAudit(appAuditId);
  if (appAudit == null) {
    logger.error("appAudit:id={} is not exist",appAuditId);
    return DataFormatCheckResult.fail(String.format("??id=%s???",appAuditId));
  }
  long appId=appAudit.getAppId();
  AppDesc appDesc=appService.getByAppId(appId);
  if (appDesc == null) {
    logger.error("appDesc:id={} is not exist");
    return DataFormatCheckResult.fail(String.format("appId=%s???",appId));
  }
  int type=appDesc.getType();
  for (  String nodeInfo : nodeInfoList) {
    nodeInfo=StringUtils.trim(nodeInfo);
    if (StringUtils.isBlank(nodeInfo)) {
      return DataFormatCheckResult.fail(String.format("????%s?????",appDeployText));
    }
    String[] array=nodeInfo.split(ConstUtils.COLON);
    if (array == null || array.length == 0) {
      return DataFormatCheckResult.fail(String.format("????%s?????",appDeployText));
    }
    String masterHost=null;
    String memSize=null;
    String slaveHost=null;
    if (TypeUtil.isRedisCluster(type)) {
      if (array.length == 2) {
        masterHost=array[0];
        memSize=array[1];
      }
 else       if (array.length == 3) {
        masterHost=array[0];
        memSize=array[1];
        slaveHost=array[2];
      }
 else {
        return DataFormatCheckResult.fail(String.format("?????%s, ????!",nodeInfo));
      }
    }
 else     if (TypeUtil.isRedisSentinel(type)) {
      if (array.length == 3) {
        masterHost=array[0];
        memSize=array[1];
        slaveHost=array[2];
      }
 else       if (array.length == 1) {
        masterHost=array[0];
      }
 else {
        return DataFormatCheckResult.fail(String.format("?????%s, ????!",nodeInfo));
      }
    }
 else     if (TypeUtil.isRedisStandalone(type)) {
      if (array.length == 2) {
        masterHost=array[0];
        memSize=array[1];
      }
 else {
        return DataFormatCheckResult.fail(String.format("?????%s, ????!",nodeInfo));
      }
    }
    if (!checkHostExist(masterHost)) {
      return DataFormatCheckResult.fail(String.format("%s??ip=%s?????????????!",nodeInfo,masterHost));
    }
    if (StringUtils.isNotBlank(memSize) && !NumberUtils.isDigits(memSize)) {
      return DataFormatCheckResult.fail(String.format("%s????memSize=%s????!",nodeInfo,memSize));
    }
    if (StringUtils.isNotBlank(slaveHost) && !checkHostExist(slaveHost)) {
      return DataFormatCheckResult.fail(String.format("%s??ip=%s?????????????!",nodeInfo,slaveHost));
    }
  }
  if (TypeUtil.isRedisSentinel(type)) {
    return checkSentinelAppDeploy(nodeInfoList);
  }
 else   if (TypeUtil.isRedisStandalone(type)) {
    return checkStandaloneAppDeploy(nodeInfoList);
  }
  return DataFormatCheckResult.success("????????????????!");
}
