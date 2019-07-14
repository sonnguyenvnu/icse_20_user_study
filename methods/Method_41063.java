public void scheduleJob(Map<String,Object> abstractJobInfo,Map<String,Object> abstractTriggerInfo) throws Exception {
  try {
    String triggerClassName=(String)abstractTriggerInfo.remove("triggerClass");
    if (triggerClassName == null) {
      throw new IllegalArgumentException("No triggerClass specified");
    }
    Class<?> triggerClass=Class.forName(triggerClassName);
    Trigger trigger=(Trigger)triggerClass.newInstance();
    String jobDetailClassName=(String)abstractJobInfo.remove("jobDetailClass");
    if (jobDetailClassName == null) {
      throw new IllegalArgumentException("No jobDetailClass specified");
    }
    Class<?> jobDetailClass=Class.forName(jobDetailClassName);
    JobDetail jobDetail=(JobDetail)jobDetailClass.newInstance();
    String jobClassName=(String)abstractJobInfo.remove("jobClass");
    if (jobClassName == null) {
      throw new IllegalArgumentException("No jobClass specified");
    }
    Class<?> jobClass=Class.forName(jobClassName);
    abstractJobInfo.put("jobClass",jobClass);
    for (    Map.Entry<String,Object> entry : abstractTriggerInfo.entrySet()) {
      String key=entry.getKey();
      Object value=entry.getValue();
      if ("jobDataMap".equals(key)) {
        value=new JobDataMap((Map<?,?>)value);
      }
      invokeSetter(trigger,key,value);
    }
    for (    Map.Entry<String,Object> entry : abstractJobInfo.entrySet()) {
      String key=entry.getKey();
      Object value=entry.getValue();
      if ("jobDataMap".equals(key)) {
        value=new JobDataMap((Map<?,?>)value);
      }
      invokeSetter(jobDetail,key,value);
    }
    AbstractTrigger<?> at=(AbstractTrigger<?>)trigger;
    at.setKey(new TriggerKey(at.getName(),at.getGroup()));
    Date startDate=at.getStartTime();
    if (startDate == null || startDate.before(new Date())) {
      at.setStartTime(new Date());
    }
    scheduler.deleteJob(jobDetail.getKey());
    scheduler.scheduleJob(jobDetail,trigger);
  }
 catch (  Exception e) {
    throw newPlainException(e);
  }
}
