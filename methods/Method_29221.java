@Override public boolean deployServerCollection(long hostId,String ip){
  Assert.hasText(ip);
  Map<String,Object> dataMap=new HashMap<String,Object>();
  dataMap.put(ConstUtils.HOST_KEY,ip);
  JobKey jobKey=JobKey.jobKey(ConstUtils.SERVER_JOB_NAME,ConstUtils.SERVER_JOB_GROUP);
  TriggerKey triggerKey=TriggerKey.triggerKey(ip,ConstUtils.SERVER_TRIGGER_GROUP + ip);
  boolean result=schedulerCenter.deployJobByCron(jobKey,triggerKey,dataMap,ScheduleUtil.getFiveMinuteCronByHostId(hostId),false);
  return result;
}
