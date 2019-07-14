public void jobWasExecuted(JobExecutionContext context,JobExecutionException jobException){
synchronized (executingJobs) {
    executingJobs.remove(((OperableTrigger)context.getTrigger()).getFireInstanceId());
  }
}
