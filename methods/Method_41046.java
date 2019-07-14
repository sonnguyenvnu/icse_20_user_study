private List<TriggerListener> buildTriggerListenerList() throws SchedulerException {
  List<TriggerListener> allListeners=new LinkedList<TriggerListener>();
  allListeners.addAll(getListenerManager().getTriggerListeners());
  allListeners.addAll(getInternalTriggerListeners());
  return allListeners;
}
