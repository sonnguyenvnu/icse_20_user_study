@Override protected boolean shouldNotify(InstanceEvent event,Instance instance){
  if (event instanceof InstanceStatusChangedEvent) {
    InstanceStatusChangedEvent statusChange=(InstanceStatusChangedEvent)event;
    String from=getLastStatus(event.getInstance());
    String to=statusChange.getStatusInfo().getStatus();
    return Arrays.binarySearch(ignoreChanges,from + ":" + to) < 0 && Arrays.binarySearch(ignoreChanges,"*:" + to) < 0 && Arrays.binarySearch(ignoreChanges,from + ":*") < 0;
  }
  return false;
}
