/** 
 * ??????
 * @param appUser
 * @param appId
 * @param isSuccess
 */
public void noticeOfflineApp(AppUser appUser,Long appId,boolean isSuccess){
  AppDetailVO appDetailVO=appStatsCenter.getAppDetail(appId);
  StringBuilder mailContent=new StringBuilder();
  mailContent.append(appUser.getChName()).append(",???appid=").append(appId);
  mailContent.append("????,?????").append(isSuccess ? "??" : "??");
  mailContent.append(",???!");
  emailComponent.sendMail("?CacheCloud?????",mailContent.toString(),appDetailVO.getEmailList(),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
