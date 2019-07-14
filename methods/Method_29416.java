@Override public boolean modifyInstanceConfig(long appId,Long appAuditId,String host,int port,String instanceConfigKey,String instanceConfigValue){
  Assert.isTrue(appAuditId != null && appAuditId > 0L);
  Assert.isTrue(StringUtils.isNotBlank(host));
  Assert.isTrue(port > 0);
  Assert.isTrue(StringUtils.isNotBlank(instanceConfigKey));
  Assert.isTrue(StringUtils.isNotBlank(instanceConfigValue));
  boolean isModify=redisDeployCenter.modifyInstanceConfig(appId,host,port,instanceConfigKey,instanceConfigValue);
  if (isModify) {
    appAuditDao.updateAppAudit(appAuditId,AppCheckEnum.APP_ALLOCATE_RESOURCE.value());
  }
  return isModify;
}
