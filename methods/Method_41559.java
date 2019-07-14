public void triggersPaused(String triggerGroup){
  Iterator<SchedulerListener> itr=listeners.iterator();
  while (itr.hasNext()) {
    SchedulerListener l=itr.next();
    l.triggersPaused(triggerGroup);
  }
}
