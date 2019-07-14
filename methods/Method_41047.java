private List<JobListener> buildJobListenerList() throws SchedulerException {
  List<JobListener> allListeners=new LinkedList<JobListener>();
  allListeners.addAll(getListenerManager().getJobListeners());
  allListeners.addAll(getInternalJobListeners());
  return allListeners;
}
