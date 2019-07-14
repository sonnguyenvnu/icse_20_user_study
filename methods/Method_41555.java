public void jobExecutionVetoed(JobExecutionContext context){
  Iterator<JobListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    JobListener jl=itr.next();
    jl.jobExecutionVetoed(context);
  }
}
