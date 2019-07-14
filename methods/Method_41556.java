public void jobWasExecuted(JobExecutionContext context,JobExecutionException jobException){
  Iterator<JobListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    JobListener jl=itr.next();
    jl.jobWasExecuted(context,jobException);
  }
}
