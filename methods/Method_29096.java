private AppClientExceptionStat generate(String clientIp,long collectTime,long reportTime,Map<String,Object> map){
  String exceptionClass=MapUtils.getString(map,ClientReportConstant.EXCEPTION_CLASS,"");
  Long exceptionCount=MapUtils.getLong(map,ClientReportConstant.EXCEPTION_COUNT,0L);
  int exceptionType=MapUtils.getInteger(map,ClientReportConstant.EXCEPTION_TYPE,ClientExceptionType.REDIS_TYPE.getType());
  String host=null;
  Integer port=null;
  Integer instanceId=null;
  long appId;
  if (ClientExceptionType.REDIS_TYPE.getType() == exceptionType) {
    String hostPort=MapUtils.getString(map,ClientReportConstant.EXCEPTION_HOST_PORT,"");
    if (StringUtils.isEmpty(hostPort)) {
      logger.warn("hostPort is empty",hostPort);
      return null;
    }
    int index=hostPort.indexOf(":");
    if (index <= 0) {
      logger.warn("hostPort {} format is wrong",hostPort);
      return null;
    }
    host=hostPort.substring(0,index);
    port=NumberUtils.toInt(hostPort.substring(index + 1));
    InstanceInfo instanceInfo=clientReportInstanceService.getInstanceInfoByHostPort(host,port);
    if (instanceInfo == null) {
      return null;
    }
    instanceId=instanceInfo.getId();
    appId=instanceInfo.getAppId();
  }
 else {
    List<AppClientVersion> appClientVersion=appClientVersionDao.getByClientIp(clientIp);
    if (CollectionUtils.isNotEmpty(appClientVersion)) {
      appId=appClientVersion.get(0).getAppId();
    }
 else {
      appId=0;
    }
  }
  AppClientExceptionStat stat=new AppClientExceptionStat();
  stat.setAppId(appId);
  stat.setClientIp(clientIp);
  stat.setReportTime(new Date(reportTime));
  stat.setCollectTime(collectTime);
  stat.setCreateTime(new Date());
  stat.setExceptionClass(exceptionClass);
  stat.setExceptionCount(exceptionCount);
  stat.setInstanceHost(host);
  stat.setInstancePort(port);
  stat.setInstanceId(instanceId);
  stat.setType(exceptionType);
  return stat;
}
