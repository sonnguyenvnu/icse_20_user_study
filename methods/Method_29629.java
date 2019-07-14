/** 
 * ??????
 * @param appUser
 * @param appAudit
 */
public void noticeUserResult(AppUser appUser,AppAudit appAudit){
  if (appAudit == null) {
    return;
  }
  StringBuffer mailContent=new StringBuffer();
  if (AppCheckEnum.APP_WATING_CHECK.value().equals(appAudit.getStatus())) {
    mailContent.append(appUser.getChName() + "?????CacheCloud????????????<br/>");
  }
 else   if (AppCheckEnum.APP_PASS.value().equals(appAudit.getStatus())) {
    mailContent.append("????????????????????Cachecloud??<br/>");
  }
 else   if (AppCheckEnum.APP_REJECT.value().equals(appAudit.getStatus())) {
    mailContent.append("?????????????: " + appAudit.getRefuseReason());
  }
  emailComponent.sendMail("?CacheCloud?????",mailContent.toString(),Arrays.asList(appUser.getEmail()),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
