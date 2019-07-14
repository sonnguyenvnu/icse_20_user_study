@Override public String executeCommand(long appId,String host,int port,String command){
  AppDesc appDesc=appDao.getAppDescById(appId);
  if (appDesc == null) {
    return "not exist appId";
  }
  if (AppDescEnum.AppTest.NOT_TEST.getValue() == appDesc.getIsTest()) {
    if (!RedisReadOnlyCommandEnum.contains(command)) {
      return "online app only support read-only and safe command ";
    }
  }
  String password=appDesc.getPassword();
  String shell=RedisProtocol.getExecuteCommandShell(host,port,password,command);
  logger.warn("executeRedisShell={}",shell);
  return machineCenter.executeShell(host,shell);
}
