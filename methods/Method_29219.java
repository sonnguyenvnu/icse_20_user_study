@Override public String showInstanceRecentLog(InstanceInfo instanceInfo,int maxLineNum){
  String host=instanceInfo.getIp();
  int port=instanceInfo.getPort();
  int type=instanceInfo.getType();
  String logType="";
  if (TypeUtil.isRedisDataType(type)) {
    logType="redis-";
  }
 else   if (TypeUtil.isRedisSentinel(type)) {
    logType="redis-sentinel-";
  }
  String remoteFilePath=MachineProtocol.LOG_DIR + logType + port + "-*.log";
  StringBuilder command=new StringBuilder();
  command.append("/usr/bin/tail -n").append(maxLineNum).append(" ").append(remoteFilePath);
  try {
    return SSHUtil.execute(host,command.toString());
  }
 catch (  SSHException e) {
    logger.error(e.getMessage(),e);
    return "";
  }
}
