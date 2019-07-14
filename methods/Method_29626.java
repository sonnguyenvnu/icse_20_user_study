/** 
 * ??????
 * @param appDesc
 * @param appAudit
 */
public void noticeAppResult(AppDesc appDesc,AppAudit appAudit){
  List<String> ccEmailList=getCCEmailList(appDesc,appAudit);
  String mailContent=VelocityUtils.createText(velocityEngine,appDesc,appAudit,new AppDailyData(),new ArrayList<InstanceAlertValueResult>(),"appAudit.vm","UTF-8");
  AppUser appUser=userService.get(appDesc.getUserId());
  emailComponent.sendMail("?CacheCloud?????",mailContent,Arrays.asList(appUser.getEmail()),ccEmailList);
}
