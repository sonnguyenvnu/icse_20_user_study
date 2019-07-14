/** 
 * A?????????S?????
 * @param appDesc
 * @return
 */
private List<String> getCCEmailList(AppDesc appDesc){
  Set<String> ccEmailSet=new LinkedHashSet<String>();
  if (appDesc.isVeryImportant()) {
    for (    String email : emailComponent.getAdminEmail().split(ConstUtils.COMMA)) {
      ccEmailSet.add(email);
    }
  }
  if (appDesc.isSuperImportant()) {
    ccEmailSet.addAll(ConstUtils.LEADER_EMAIL_LIST);
  }
  return new ArrayList<String>(ccEmailSet);
}
