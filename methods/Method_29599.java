@Override public void updateUserAuditStatus(Long id,Integer status){
  appAuditDao.updateAppAudit(id,status);
}
