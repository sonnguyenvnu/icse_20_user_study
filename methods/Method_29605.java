@Override public AppAudit saveInstanceChangeConfig(AppDesc appDesc,AppUser appUser,Long instanceId,String instanceConfigKey,String instanceConfigValue,String instanceConfigReason,AppAuditType instanceModifyConfig){
  AppAudit appAudit=new AppAudit();
  long appId=appDesc.getAppId();
  appAudit.setAppId(appId);
  appAudit.setUserId(appUser.getId());
  appAudit.setUserName(appUser.getName());
  appAudit.setModifyTime(new Date());
  appAudit.setParam1(String.valueOf(instanceId));
  appAudit.setParam2(instanceConfigKey);
  appAudit.setParam3(instanceConfigValue);
  InstanceInfo instanceInfo=instanceDao.getInstanceInfoById(instanceId);
  String hostPort=instanceInfo == null ? "" : (instanceInfo.getIp() + ":" + instanceInfo.getPort());
  appAudit.setInfo("appId=" + appId + "??" + hostPort + "?????????:" + instanceConfigKey + ", ???: " + instanceConfigValue + ", ????: " + instanceConfigReason);
  appAudit.setStatus(AppCheckEnum.APP_WATING_CHECK.value());
  appAudit.setType(instanceModifyConfig.getValue());
  appAuditDao.insertAppAudit(appAudit);
  AppAuditLog appAuditLog=AppAuditLog.generate(appDesc,appUser,appAudit.getId(),AppAuditLogTypeEnum.INSTANCE_CONFIG_APPLY);
  if (appAuditLog != null) {
    appAuditLogDao.save(appAuditLog);
  }
  return appAudit;
}
