@Override public SuccessEnum updateRefuseReason(AppAudit appAudit,AppUser userInfo){
  try {
    appAuditDao.updateRefuseReason(appAudit.getId(),appAudit.getRefuseReason());
    return SuccessEnum.SUCCESS;
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
    return SuccessEnum.FAIL;
  }
}
