@Override public String executeCommand(long appId,String command){
  if (StringUtils.isBlank(command)) {
    return "??????";
  }
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    return "app not found";
  }
  if (TypeUtil.isRedisType(appDesc.getType())) {
    return redisCenter.executeCommand(appDesc,command);
  }
  return "not support app";
}
