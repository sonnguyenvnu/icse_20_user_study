protected boolean shouldStartReminder(InstanceEvent event){
  if (event instanceof InstanceStatusChangedEvent) {
    return Arrays.binarySearch(this.reminderStatuses,((InstanceStatusChangedEvent)event).getStatusInfo().getStatus()) >= 0;
  }
  return false;
}
