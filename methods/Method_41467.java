static void setDetails(Object target,String schedulerName,String schedulerId) throws SchedulerException {
  set(target,"setInstanceName",schedulerName);
  set(target,"setInstanceId",schedulerId);
}
