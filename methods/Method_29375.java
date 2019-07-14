@Override public boolean createApp(AppDesc appDesc,AppUser appUser,String memSize){
  try {
    appService.save(appDesc);
    appService.saveAppToUser(appDesc.getAppId(),appDesc.getUserId());
    long appId=appDesc.getAppId();
    appService.updateAppKey(appId);
    AppAudit appAudit=new AppAudit();
    appAudit.setAppId(appId);
    appAudit.setUserId(appUser.getId());
    appAudit.setUserName(appUser.getName());
    appAudit.setModifyTime(new Date());
    appAudit.setParam1(memSize);
    appAudit.setParam2(appDesc.getTypeDesc());
    appAudit.setInfo("??:" + appDesc.getTypeDesc() + ";??????:" + memSize);
    appAudit.setStatus(AppCheckEnum.APP_WATING_CHECK.value());
    appAudit.setType(AppAuditType.APP_AUDIT.getValue());
    appAuditDao.insertAppAudit(appAudit);
    appEmailUtil.noticeAppResult(appDesc,appAudit);
    AppAuditLog appAuditLog=AppAuditLog.generate(appDesc,appUser,appAudit.getId(),AppAuditLogTypeEnum.APP_DESC_APPLY);
    if (appAuditLog != null) {
      appAuditLogDao.save(appAuditLog);
    }
    return true;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return false;
  }
}
