/** 
 * ?????
 * @param groupName
 * @param applyReason
 * @param appUser
 */
public void noticeBecomeContributor(String groupName,String applyReason,AppUser appUser){
  StringBuffer mailContent=new StringBuffer();
  mailContent.append(appUser.getChName() + "(???:" + groupName + ")????CacheCloud???<br/>");
  mailContent.append("????:<br/>" + applyReason);
  emailComponent.sendMail("?CacheCloud?????",mailContent.toString(),Arrays.asList(appUser.getEmail()),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
