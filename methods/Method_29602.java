@Override public List<AppAudit> getAppAudits(Integer status,Integer type){
  List<AppAudit> list=appAuditDao.selectWaitAppAudits(status,type);
  for (Iterator<AppAudit> i=list.iterator(); i.hasNext(); ) {
    AppAudit appAudit=i.next();
    AppDesc appDesc=appDao.getAppDescById(appAudit.getAppId());
    appAudit.setAppDesc(appDesc);
  }
  return list;
}
