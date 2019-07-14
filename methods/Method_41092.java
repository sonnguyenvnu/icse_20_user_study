public void jobExecutionVetoed(JobExecutionContext context){
  try {
    sendNotification(JOB_EXECUTION_VETOED,JobExecutionContextSupport.toCompositeData(context));
  }
 catch (  Exception e) {
    throw new RuntimeException(newPlainException(e));
  }
}
