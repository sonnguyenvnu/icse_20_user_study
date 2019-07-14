private List<SchedulerListener> buildSchedulerListenerList(){
  List<SchedulerListener> allListeners=new LinkedList<SchedulerListener>();
  allListeners.addAll(getListenerManager().getSchedulerListeners());
  allListeners.addAll(getInternalSchedulerListeners());
  return allListeners;
}
