/** 
 * ?????????
 * @param instanceStats
 * @param appDetailVO
 * @param appClientConnThreshold
 */
private void alertInstanceClientConn(final InstanceStats instanceStats,final AppDetailVO appDetailVO,final int appClientConnThreshold){
  String instanceHostPort=instanceStats.getIp() + ":" + instanceStats.getPort();
  String content=String.format("??(%s,??(%s))????????-??%s-????%s-?????",instanceHostPort,instanceStats.getAppId(),appClientConnThreshold,instanceStats.getCurrConnections());
  String title="CacheCloud??-??????????";
  logger.warn("instance title {}",title);
  logger.warn("instace content {}",content);
  emailComponent.sendMail(title,content,appDetailVO.getEmailList(),Arrays.asList(emailComponent.getAdminEmail().split(ConstUtils.COMMA)));
}
