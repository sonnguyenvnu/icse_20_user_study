/** 
 * ????????????trigger
 * @param hostId ??id
 * @param ip     ip
 * @return ??????
 */
@Override public boolean deployMachineMonitor(final long hostId,final String ip){
  Assert.isTrue(hostId > 0);
  Assert.hasText(ip);
  Map<String,Object> dataMap=new HashMap<String,Object>();
  dataMap.put(ConstUtils.HOST_KEY,ip);
  dataMap.put(ConstUtils.HOST_ID_KEY,hostId);
  JobKey jobKey=JobKey.jobKey(ConstUtils.MACHINE_MONITOR_JOB_NAME,ConstUtils.MACHINE_MONITOR_JOB_GROUP);
  TriggerKey triggerKey=TriggerKey.triggerKey(ip,ConstUtils.MACHINE_MONITOR_TRIGGER_GROUP + hostId);
  boolean result=schedulerCenter.deployJobByCron(jobKey,triggerKey,dataMap,ScheduleUtil.getHourCronByHostId(hostId),false);
  return result;
}
