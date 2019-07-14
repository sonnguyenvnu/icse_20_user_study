public boolean unscheduleJob(String triggerName,String triggerGroup) throws Exception {
  try {
    return scheduler.unscheduleJob(triggerKey(triggerName,triggerGroup));
  }
 catch (  Exception e) {
    throw newPlainException(e);
  }
}
