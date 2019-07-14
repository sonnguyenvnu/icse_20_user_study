/** 
 * ??????
 * @param appDesc
 * @param appAudit
 * @return
 */
private List<String> getCCEmailList(AppDesc appDesc,AppAudit appAudit){
  Set<String> ccEmailSet=new LinkedHashSet<String>();
  for (  String email : emailComponent.getAdminEmail().split(ConstUtils.COMMA)) {
    ccEmailSet.add(email);
  }
  if (appDesc.isSuperImportant() && AppAuditType.APP_AUDIT.getValue() == appAudit.getType()) {
    ccEmailSet.addAll(ConstUtils.LEADER_EMAIL_LIST);
  }
  return new ArrayList<String>(ccEmailSet);
}
