public void notifyJobListenersToBeExecuted(JobExecutionContext jec) throws SchedulerException {
  List<JobListener> jobListeners=buildJobListenerList();
  for (  JobListener jl : jobListeners) {
    try {
      if (!matchJobListener(jl,jec.getJobDetail().getKey()))       continue;
      jl.jobToBeExecuted(jec);
    }
 catch (    Exception e) {
      SchedulerException se=new SchedulerException("JobListener '" + jl.getName() + "' threw exception: " + e.getMessage(),e);
      throw se;
    }
  }
}
