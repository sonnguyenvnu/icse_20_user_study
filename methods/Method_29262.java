@Override public boolean deployRedisSlowLogCollection(long appId,String host,int port){
  Assert.isTrue(appId > 0);
  Assert.hasText(host);
  Assert.isTrue(port > 0);
  Map<String,Object> dataMap=new HashMap<String,Object>();
  dataMap.put(ConstUtils.HOST_KEY,host);
  dataMap.put(ConstUtils.PORT_KEY,port);
  dataMap.put(ConstUtils.APP_KEY,appId);
  JobKey jobKey=JobKey.jobKey(ConstUtils.REDIS_SLOWLOG_JOB_NAME,ConstUtils.REDIS_SLOWLOG_JOB_GROUP);
  TriggerKey triggerKey=TriggerKey.triggerKey(ObjectConvert.linkIpAndPort(host,port),ConstUtils.REDIS_SLOWLOG_TRIGGER_GROUP + appId);
  boolean result=schedulerCenter.deployJobByCron(jobKey,triggerKey,dataMap,ScheduleUtil.getRedisSlowLogCron(appId),false);
  return result;
}
