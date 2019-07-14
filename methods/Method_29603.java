@Override public AppAudit saveAppScaleApply(AppDesc appDesc,AppUser appUser,String applyMemSize,String appScaleReason,AppAuditType appScale){
  AppAudit appAudit=new AppAudit();
  appAudit.setAppId(appDesc.getAppId());
  appAudit.setUserId(appUser.getId());
  appAudit.setUserName(appUser.getName());
  appAudit.setModifyTime(new Date());
  appAudit.setParam1(applyMemSize);
  appAudit.setParam2(appScaleReason);
  appAudit.setInfo("????---????:" + applyMemSize + ", ????: " + appScaleReason);
  appAudit.setStatus(AppCheckEnum.APP_WATING_CHECK.value());
  appAudit.setType(appScale.getValue());
  appAuditDao.insertAppAudit(appAudit);
  AppAuditLog appAuditLog=AppAuditLog.generate(appDesc,appUser,appAudit.getId(),AppAuditLogTypeEnum.APP_SCALE_APPLY);
  if (appAuditLog != null) {
    appAuditLogDao.save(appAuditLog);
  }
  return appAudit;
}
