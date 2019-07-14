@Override public boolean modifyAppConfig(Long appId,Long appAuditId,String key,String value){
  Assert.isTrue(appId != null && appId > 0L);
  Assert.isTrue(appAuditId != null && appAuditId > 0L);
  Assert.isTrue(StringUtils.isNotBlank(key));
  Assert.isTrue(StringUtils.isNotBlank(value));
  boolean isModify=redisDeployCenter.modifyAppConfig(appId,key,value);
  if (isModify) {
    appAuditDao.updateAppAudit(appAuditId,AppCheckEnum.APP_ALLOCATE_RESOURCE.value());
  }
  return isModify;
}
