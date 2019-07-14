@Override public String executeCommand(String host,int port,String command){
  if (StringUtils.isBlank(host) || port == 0) {
    return "host or port is null";
  }
  InstanceInfo instanceInfo=instanceDao.getAllInstByIpAndPort(host,port);
  if (instanceInfo == null) {
    return "instance not exist";
  }
  if (TypeUtil.isRedisType(instanceInfo.getType())) {
    return redisCenter.executeCommand(instanceInfo.getAppId(),host,port,command);
  }
  return "not support type";
}
