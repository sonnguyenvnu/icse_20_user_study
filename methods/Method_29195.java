/** 
 * ??????
 * @param info
 * @return
 */
private String generateMessage(InstanceInfo info,boolean isEmail){
  StringBuffer message=new StringBuffer();
  long appId=info.getAppId();
  AppDesc appDesc=appDao.getAppDescById(appId);
  message.append("CacheCloud??-??(" + info.getIp() + ":" + info.getPort() + ")-");
  if (info.getStatus() == InstanceStatusEnum.ERROR_STATUS.getStatus()) {
    message.append("??????????");
  }
 else   if (info.getStatus() == InstanceStatusEnum.GOOD_STATUS.getStatus()) {
    message.append("??????????");
  }
  if (isEmail) {
    message.append(", appId:");
    message.append(appId + "-" + appDesc.getName());
  }
 else {
    message.append("-appId(" + appId + "-" + appDesc.getName() + ")");
  }
  return message.toString();
}
