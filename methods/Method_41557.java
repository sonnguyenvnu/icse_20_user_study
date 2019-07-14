public void jobDeleted(JobKey jobKey){
  Iterator<SchedulerListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    SchedulerListener l=itr.next();
    l.jobDeleted(jobKey);
  }
}
