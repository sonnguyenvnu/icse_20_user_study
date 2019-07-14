/** 
 * ????
 * @param noticeContent
 * @return
 */
public boolean noticeAllUser(String noticeContent){
  if (StringUtils.isBlank(noticeContent)) {
    return false;
  }
  try {
    String mailTitle="?CacheCloud?-????";
    StringBuffer mailContent=new StringBuffer();
    String[] noticeArray=noticeContent.split(ConstUtils.NEXT_LINE);
    for (    String noticeLine : noticeArray) {
      mailContent.append(noticeLine).append("<br/>");
    }
    List<String> emailList=new ArrayList<String>();
    List<AppUser> appUserList=userService.getUserList(null);
    if (CollectionUtils.isEmpty(appUserList)) {
      return false;
    }
    for (    AppUser appUser : appUserList) {
      String email=appUser.getEmail();
      if (StringUtils.isBlank(email)) {
        continue;
      }
      emailList.add(email);
    }
    return emailComponent.sendMail(mailTitle,mailContent.toString(),emailList,Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
  }
 catch (  Exception e) {
    logger.error(e.getMessage(),e);
  }
  return false;
}
