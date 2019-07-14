public void notifySchedulerListenersUnscheduled(TriggerKey triggerKey){
  List<SchedulerListener> schedListeners=buildSchedulerListenerList();
  for (  SchedulerListener sl : schedListeners) {
    try {
      if (triggerKey == null)       sl.schedulingDataCleared();
 else       sl.jobUnscheduled(triggerKey);
    }
 catch (    Exception e) {
      getLog().error("Error while notifying SchedulerListener of unscheduled job." + "  Triger=" + (triggerKey == null ? "ALL DATA" : triggerKey),e);
    }
  }
}
