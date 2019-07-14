public void jobToBeExecuted(JobExecutionContext context){
  try {
    sendNotification(JOB_TO_BE_EXECUTED,JobExecutionContextSupport.toCompositeData(context));
  }
 catch (  Exception e) {
    throw new RuntimeException(newPlainException(e));
  }
}
