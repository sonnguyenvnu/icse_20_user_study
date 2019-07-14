@Override public DataFormatCheckResult checkHorizontalNodes(Long appAuditId,String masterSizeSlave){
  if (appAuditId == null) {
    logger.error("appAuditId is null");
    return DataFormatCheckResult.fail("??id????!");
  }
  if (StringUtils.isBlank(masterSizeSlave)) {
    logger.error("masterSizeSlave is null");
    return DataFormatCheckResult.fail("????????!");
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
  String[] array=masterSizeSlave.split(ConstUtils.COLON);
  if (array == null || array.length == 0) {
    return DataFormatCheckResult.fail(String.format("????%s????",masterSizeSlave));
  }
  String masterHost=null;
  String memSize=null;
  String slaveHost=null;
  if (array.length == 2) {
    masterHost=array[0];
    memSize=array[1];
  }
 else   if (array.length == 3) {
    masterHost=array[0];
    memSize=array[1];
    slaveHost=array[2];
  }
 else {
    return DataFormatCheckResult.fail(String.format("????%s, ????!",masterSizeSlave));
  }
  if (!checkHostExist(masterHost)) {
    return DataFormatCheckResult.fail(String.format("%s??ip=%s?????????????!",masterSizeSlave,masterHost));
  }
  if (StringUtils.isNotBlank(memSize) && !NumberUtils.isDigits(memSize)) {
    return DataFormatCheckResult.fail(String.format("%s????memSize=%s????!",masterSizeSlave,memSize));
  }
  if (StringUtils.isNotBlank(slaveHost) && !checkHostExist(slaveHost)) {
    return DataFormatCheckResult.fail(String.format("%s??ip=%s?????????????!",masterSizeSlave,slaveHost));
  }
  return DataFormatCheckResult.success("????????????????!");
}
