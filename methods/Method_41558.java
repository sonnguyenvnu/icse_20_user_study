public void jobScheduled(Trigger trigger){
  Iterator<SchedulerListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    SchedulerListener l=itr.next();
    l.jobScheduled(trigger);
  }
}
