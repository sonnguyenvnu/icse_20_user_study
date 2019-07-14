public boolean interruptJob(String jobName,String jobGroupName) throws Exception {
  try {
    return scheduler.interrupt(jobKey(jobName,jobGroupName));
  }
 catch (  Exception e) {
    throw newPlainException(e);
  }
}
