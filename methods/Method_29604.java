@Override public AppAudit saveAppChangeConfig(AppDesc appDesc,AppUser appUser,Long instanceId,String appConfigKey,String appConfigValue,String appConfigReason,AppAuditType modifyConfig){
  AppAudit appAudit=new AppAudit();
  appAudit.setAppId(appDesc.getAppId());
  appAudit.setUserId(appUser.getId());
  appAudit.setUserName(appUser.getName());
  appAudit.setModifyTime(new Date());
  appAudit.setParam1(String.valueOf(instanceId));
  appAudit.setParam2(appConfigKey);
  appAudit.setParam3(appConfigValue);
  appAudit.setInfo("?????:" + appConfigKey + ", ???: " + appConfigValue + ", ????: " + appConfigReason);
  appAudit.setStatus(AppCheckEnum.APP_WATING_CHECK.value());
  appAudit.setType(modifyConfig.getValue());
  appAuditDao.insertAppAudit(appAudit);
  AppAuditLog appAuditLog=AppAuditLog.generate(appDesc,appUser,appAudit.getId(),AppAuditLogTypeEnum.APP_CONFIG_APPLY);
  if (appAuditLog != null) {
    appAuditLogDao.save(appAuditLog);
  }
  return appAudit;
}
