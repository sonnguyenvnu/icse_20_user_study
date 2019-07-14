/** 
 * ???????
 * @param appDetailVO
 * @param appClientConnThreshold
 * @param instanceCount
 */
private void alertAppClientConn(final AppDetailVO appDetailVO,final int appClientConnThreshold,final int instanceCount){
  AppDesc appDesc=appDetailVO.getAppDesc();
  String content=String.format("??(%s)-????????-?????????%s-????%s(????:%s)-?????",appDesc.getAppId(),appClientConnThreshold,appDetailVO.getConn(),instanceCount);
  String title="CacheCloud??-????????";
  logger.warn("app title {}",title);
  logger.warn("app content {}",content);
  emailComponent.sendMail(title,content,appDetailVO.getEmailList(),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
