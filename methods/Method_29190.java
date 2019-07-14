/** 
 * @param appDetailVO
 */
private void alertAppMemUse(final AppDetailVO appDetailVO){
  AppDesc appDesc=appDetailVO.getAppDesc();
  String content=String.format("??(%s)-???????-?????%s-???????%s-?????",appDesc.getAppId(),appDesc.getMemAlertValue(),appDetailVO.getMemUsePercent());
  String title="CacheCloud??-?????????";
  emailComponent.sendMail(title,content,appDetailVO.getEmailList(),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
