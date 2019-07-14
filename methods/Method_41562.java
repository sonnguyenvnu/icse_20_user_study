public void jobsResumed(String jobGroup){
  Iterator<SchedulerListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    SchedulerListener l=itr.next();
    l.jobsResumed(jobGroup);
  }
}
