public void jobWasExecuted(JobExecutionContext context,JobExecutionException jobException){
  try {
    sendNotification(JOB_WAS_EXECUTED,JobExecutionContextSupport.toCompositeData(context));
  }
 catch (  Exception e) {
    throw new RuntimeException(newPlainException(e));
  }
}
