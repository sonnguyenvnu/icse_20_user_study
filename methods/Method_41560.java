public void jobPaused(JobKey key){
  Iterator<SchedulerListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    SchedulerListener l=itr.next();
    l.jobPaused(key);
  }
}
