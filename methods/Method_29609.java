@Override public SuccessEnum changeAppAlertConfig(long appId,int memAlertValue,int clientConnAlertValue,AppUser appUser){
  if (appId <= 0 || memAlertValue <= 0 || clientConnAlertValue <= 0) {
    return SuccessEnum.FAIL;
  }
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    return SuccessEnum.FAIL;
  }
  try {
    appDesc.setMemAlertValue(memAlertValue);
    appDesc.setClientConnAlertValue(clientConnAlertValue);
    appDao.update(appDesc);
    AppAuditLog appAuditLog=AppAuditLog.generate(appDesc,appUser,0L,AppAuditLogTypeEnum.APP_CHANGE_ALERT);
    if (appAuditLog != null) {
      appAuditLogDao.save(appAuditLog);
    }
    return SuccessEnum.SUCCESS;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return SuccessEnum.FAIL;
  }
}
