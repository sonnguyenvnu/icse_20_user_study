public void schedulerShuttingdown(){
  Iterator<SchedulerListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    SchedulerListener l=itr.next();
    l.schedulerShuttingdown();
  }
}
