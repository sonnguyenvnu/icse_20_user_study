@Override public List<AppAudit> getAppAuditListByAppId(Long appId){
  Assert.isTrue(appId != null && appId > 0L);
  List<AppAudit> appAudits=appAuditDao.getAppAuditByAppId(appId);
  if (CollectionUtils.isNotEmpty(appAudits)) {
    for (    AppAudit appAudit : appAudits) {
      Long appAuditId=appAudit.getId();
      AppAuditLog log=appAuditLogDao.getAuditByType(appAuditId,AppAuditLogTypeEnum.APP_CHECK.value());
      if (log != null) {
        log.setAppUser(appUserDao.get(log.getUserId()));
      }
      appAudit.setAppAuditLog(log);
    }
  }
  return appAudits;
}
