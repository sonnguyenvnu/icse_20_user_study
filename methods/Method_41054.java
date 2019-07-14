public void notifySchedulerListenersPausedTrigger(TriggerKey triggerKey){
  List<SchedulerListener> schedListeners=buildSchedulerListenerList();
  for (  SchedulerListener sl : schedListeners) {
    try {
      sl.triggerPaused(triggerKey);
    }
 catch (    Exception e) {
      getLog().error("Error while notifying SchedulerListener of paused trigger: " + triggerKey,e);
    }
  }
}
